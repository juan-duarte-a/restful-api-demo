package app.jdev.restfulapidemo.model;

import java.time.LocalDate;
import java.util.List;

public record NewOrderDTO(LocalDate date, Long clientId, List<ProductQuantityDTO> products) {

}
