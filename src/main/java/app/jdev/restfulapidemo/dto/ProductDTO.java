package app.jdev.restfulapidemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
        Long id,
        @NotBlank(message = "Name should not be blank") String name,
        @Positive(message = "Price must be a positive value") double price
) implements DTO<Long> {
    
}
