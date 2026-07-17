package andreasaderi.L5.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReservationDTO(
        @NotNull(message = "Event field can't be null")
        UUID eventId
) {
}
