package app.jdev.restfulapidemo.mapper;

import org.springframework.stereotype.Component;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.model.ClientDTO;
import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.model.OrderDTO;

@Component
public class OrderMapper implements Mapper<Order, Long> {

    private final ClientMapper clientMapper;
    private final OrderProductMapper orderProductMapper;

    public OrderMapper(ClientMapper clientMapper, OrderProductMapper orderProductMapper) {
        this.clientMapper = clientMapper;
        this.orderProductMapper = orderProductMapper;
    }

    @Override
    public Order mapToEntity(DTO<Long> dto) {
        OrderDTO orderDTO = (OrderDTO) dto;
        Order order = new Order(orderDTO.date(), clientMapper.mapToEntity(orderDTO.client()),
                orderDTO.products().stream().map(orderProductMapper::mapToEntity).toList());
        order.setId(orderDTO.id());
        return order;
    }

    @Override
    public DTO<Long> mapToDTO(Order order) {
        return new OrderDTO(order.getId(),
                order.getDate(),
                (ClientDTO) clientMapper.mapToDTO(order.getClient()),
                order.getOrderProducts().stream().map(orderProductMapper::mapToDTO).toList());
    }

    @Override
    public Order updateAndMapToEntity(Long id, DTO<Long> dto) {
        Order order = mapToEntity(dto);
        order.setId(id);
        return order;
    }

}
