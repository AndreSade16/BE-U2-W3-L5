package andreasaderi.L5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @Email
        String email,
        @NotBlank(message = "Password field can't be blank")
        @Size(min = 8, message = "Password value must be at least 8 characters long")
        String password
) {
}
