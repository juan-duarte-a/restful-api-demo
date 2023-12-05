package app.jdev.restfulapidemo.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO(
        Long id,
        LocalDate date,
        ClientDTO client,
        List<DTO<Long>> products,
        double totalPrice
) implements DTO<Long> {

}
