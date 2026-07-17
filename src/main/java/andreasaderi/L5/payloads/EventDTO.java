package andreasaderi.L5.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EventDTO(
        @NotBlank(message = "Title field can't be blank nor null")
        @Size(min = 3, message = "Title field must be at least 3 characters long")
        String title,
        @NotBlank(message = "Description field can't be blank nor null")
        @Size(min = 3, message = "Description field must be at least 3 characters long")
        String description,
        @Future(message = "Event date can't be set in the past")
        LocalDate date,
        @NotBlank(message = "Location field can't be blank nor null")
        @Size(min = 3, message = "Location field must be at least 3 characters long")
        String location,
        @NotNull(message = "Allowance field can't be null")
        @Positive(message = "Allowance must be more than 0")
        @Max(value = 5000, message = "Max allowance is set to 5000 spots")
        int allowance
) {
}
