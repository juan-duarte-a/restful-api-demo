package app.jdev.restfulapidemo.model;

public record ProductDTO(Long id, String name, double price) implements DTO<Long> {
    
}
