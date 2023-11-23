package app.jdev.restfulapidemo.mapper;

import org.springframework.stereotype.Component;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.model.ClientDTO;
import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.model.OrderDTO;

@Component
public class OrderMapper implements Mapper<Order, Long> {

    private final ClientMapper clientMapper;

    public OrderMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @Override
    public Order mapToEntity(DTO<Long> dto) {
        OrderDTO orderDTO = (OrderDTO) dto;
        ClientMapper clientMapper = new ClientMapper();
        Order order = new Order();
        order.setId(orderDTO.id());
        order.setDate(orderDTO.date());
        order.setClient(clientMapper.mapToEntity(orderDTO.client()));
        return order;
    }

    @Override
    public DTO<Long> mapToDTO(Order order) {
        return new OrderDTO(order.getId(),
                order.getDate(),
                (ClientDTO) clientMapper.mapToDTO(order.getClient()));
    }

}
