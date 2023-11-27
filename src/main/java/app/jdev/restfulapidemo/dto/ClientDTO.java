package app.jdev.restfulapidemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
        Long id,
        @NotBlank(message = "Name should not be blank") String name,
        String phoneNumber,
        @Email(message = "Invalid email") String email
) implements DTO<Long> {
    
}
