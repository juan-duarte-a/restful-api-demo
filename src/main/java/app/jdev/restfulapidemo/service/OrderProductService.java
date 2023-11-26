package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.entity.OrderProduct;
import app.jdev.restfulapidemo.repository.OrderProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public void deleteOrderProduct(OrderProduct orderProduct) {
        orderProductRepository.delete(orderProduct);
    }

}
