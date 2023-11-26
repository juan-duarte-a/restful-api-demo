package app.jdev.restfulapidemo.mapper;

import app.jdev.restfulapidemo.entity.OrderProduct;
import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.model.OrderProductDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderProductMapper implements Mapper<OrderProduct, Long> {

    @Override
    public OrderProduct mapToEntity(DTO<Long> dto) {
        OrderProductDTO orderProductDTO = (OrderProductDTO) dto;
        var orderProduct = new OrderProduct(orderProductDTO.order(),
                orderProductDTO.product(), orderProductDTO.quantity());
        orderProduct.setId(orderProductDTO.id());
        return orderProduct;
    }

    @Override
    public DTO<Long> mapToDTO(OrderProduct orderProduct) {
        return new OrderProductDTO(
                orderProduct.getId(),
                orderProduct.getOrder(),
                orderProduct.getProduct(),
                orderProduct.getQuantity());
    }

    @Override
    public OrderProduct updateAndMapToEntity(Long id, DTO<Long> dto) {
        OrderProduct orderProduct = mapToEntity(dto);
        orderProduct.setId(id);
        return orderProduct;
    }

}
