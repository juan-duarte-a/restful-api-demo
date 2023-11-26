package app.jdev.restfulapidemo.repository;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.entity.Order;

import java.util.List;

public interface OrderRepository extends EntityRepository<Order, Long> {

    List<Order> findAllByClient(Client client);

}
