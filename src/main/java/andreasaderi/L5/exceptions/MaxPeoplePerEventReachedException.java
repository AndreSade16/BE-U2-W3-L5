package andreasaderi.L5.exceptions;

public class MaxPeoplePerEventReachedException extends RuntimeException {
    public MaxPeoplePerEventReachedException(String message) {
        super(message);
    }
}
