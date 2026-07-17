package andreasaderi.L5.payloads;

import andreasaderi.L5.entities.Role;
import jakarta.validation.constraints.NotNull;

public record SetUserRoleDTO(
        @NotNull(message = "Role can't be null")
        Role role
) {
}
