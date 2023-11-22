package app.jdev.restfulapidemo.repository;

import org.springframework.data.repository.CrudRepository;

import app.jdev.restfulapidemo.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    
}
