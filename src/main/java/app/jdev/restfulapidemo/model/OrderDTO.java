package app.jdev.restfulapidemo.model;

import java.time.LocalDate;

public record OrderDTO(Long id, LocalDate date, ClientDTO client) implements DTO<Long> {
    
}
