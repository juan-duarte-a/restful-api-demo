package app.jdev.restfulapidemo.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO(
        Long id,
        LocalDate date,
        ClientDTO client,
        @JsonManagedReference List<DTO<Long>> products,
        double totalPrice
) implements DTO<Long> {

}
