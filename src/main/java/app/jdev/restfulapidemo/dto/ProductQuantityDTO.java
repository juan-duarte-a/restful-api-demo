package app.jdev.restfulapidemo.dto;

import jakarta.validation.constraints.Positive;

public record ProductQuantityDTO(
        Long productId,
        @Positive(message = "Quantity should be a positive value") Integer quantity
) {

}
