package app.jdev.restfulapidemo.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record NewOrderDTO(
        LocalDate date,
        Long clientId,
        @NotEmpty(message = "Product list should not be empty") List<ProductQuantityDTO> products
) {

}
