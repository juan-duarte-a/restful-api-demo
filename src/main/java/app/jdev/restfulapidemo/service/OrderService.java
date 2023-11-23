package app.jdev.restfulapidemo.service;

import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.mapper.OrderMapper;
import app.jdev.restfulapidemo.repository.OrderRepository;

@Service
public class OrderService extends EntityService<Order, Long> {

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        super(orderRepository, orderMapper);
    }

}
