package app.jdev.restfulapidemo.dto;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Positive;

public record OrderProductDTO(
        @JsonIgnore Long id,
        @JsonBackReference Order order,
        Product product,
        @Positive(message = "Quantity should be a positive value") int quantity
) implements DTO<Long> {

}
