package app.jdev.restfulapidemo.model;

public record ClientDTO(Long id, String name, String phoneNumber, String email) implements DTO<Long> {
    
}
