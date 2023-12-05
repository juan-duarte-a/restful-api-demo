package app.jdev.restfulapidemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Positive;

public record OrderProductDTO(
        @JsonIgnore Long id,
        @JsonIgnore Long orderId,
        DTO<Long> product,
        @Positive(message = "Quantity should be a positive value") int quantity
) implements DTO<Long> {

}
