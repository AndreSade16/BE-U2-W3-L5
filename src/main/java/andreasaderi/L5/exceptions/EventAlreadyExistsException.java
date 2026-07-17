package andreasaderi.L5.exceptions;

import java.time.LocalDate;

public class EventAlreadyExistsException extends RuntimeException {
    public EventAlreadyExistsException(String location, LocalDate date) {
        super("Event in location " + location + " set in date " + date + " already exists");
    }
}
