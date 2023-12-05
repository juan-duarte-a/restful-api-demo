package app.jdev.restfulapidemo.mapper;

import org.springframework.stereotype.Component;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.dto.ClientDTO;
import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.dto.OrderDTO;

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
        var order = new Order(
                orderDTO.id(),
                orderDTO.date(),
                clientMapper.mapToEntity(orderDTO.client()),
                orderDTO.totalPrice());

        order.setOrderProducts(orderDTO.products().stream()
                .map(orderProductDTO -> orderProductMapper.mapToEntity(orderProductDTO, order))
                .toList());

        return order;
    }

    @Override
    public DTO<Long> mapToDTO(Order order) {
        return new OrderDTO(order.getId(),
                order.getDate(),
                (ClientDTO) clientMapper.mapToDTO(order.getClient()),
                order.getOrderProducts().stream().map(orderProductMapper::mapToDTO).toList(),
                order.getTotalPrice());
    }

    @Override
    public Order updateAndMapToEntity(Long id, DTO<Long> dto) {
        Order order = mapToEntity(dto);
        order.setId(id);
        return order;
    }

}
