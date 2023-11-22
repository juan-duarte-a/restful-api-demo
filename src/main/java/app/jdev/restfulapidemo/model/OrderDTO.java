package app.jdev.restfulapidemo.model;

import java.time.LocalDate;

import app.jdev.restfulapidemo.entity.Client;

public record OrderDTO(Long id, LocalDate date, Client client) {
    
}
