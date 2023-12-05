package app.jdev.restfulapidemo.mapper;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.entity.OrderProduct;
import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.dto.OrderProductDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderProductMapper {

    private final ProductMapper productMapper;

    public OrderProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderProduct mapToEntity(DTO<Long> dto, Order order) {
        OrderProductDTO orderProductDTO = (OrderProductDTO) dto;
        var orderProduct = new OrderProduct(
                order,
                productMapper.mapToEntity(orderProductDTO.product()), 
                orderProductDTO.quantity());
        orderProduct.setId(orderProductDTO.id());
        return orderProduct;
    }

    public DTO<Long> mapToDTO(OrderProduct orderProduct) {
        return new OrderProductDTO(
                orderProduct.getId(),
                orderProduct.getOrder().getId(),
                productMapper.mapToDTO(orderProduct.getProduct()),
                orderProduct.getQuantity());
    }

}
