package andreasaderi.L5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotBlank(message = "Name field can't be blank nor null")
        @Size(min = 3, message = "Name field must be at least 3 characters long")
        String name,
        @NotBlank(message = "Surname field can't be blank nor null")
        @Size(min = 3, message = "Surname field must be at least 3 characters long")
        String surname,
        @Email(message = "Email field must be a valid email")
        @NotNull(message = "Email field can't be null")
        String email,
        @NotBlank(message = "Surname field can't be blank nor null")
        @Size(min = 8, message = "Surname field must be at least 8 characters long")
        String password
) {
}
