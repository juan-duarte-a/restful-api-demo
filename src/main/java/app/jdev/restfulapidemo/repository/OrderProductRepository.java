package app.jdev.restfulapidemo.repository;

import app.jdev.restfulapidemo.entity.OrderProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {

}
