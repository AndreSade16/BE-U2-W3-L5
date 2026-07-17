package andreasaderi.L5.exceptions;

import andreasaderi.L5.entities.User;

import java.util.UUID;

public class ReservationAlreadyExistsException extends RuntimeException {
    public ReservationAlreadyExistsException(UUID eventId, User user) {
        super("Event " + eventId + " has already had a prenotation set by user with email" + user.getEmail());
    }
}
