package app.jdev.restfulapidemo.model;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record OrderProductDTO(@JsonIgnore Long id, @JsonBackReference Order order, Product product,
                              int quantity) implements DTO<Long> {

}
