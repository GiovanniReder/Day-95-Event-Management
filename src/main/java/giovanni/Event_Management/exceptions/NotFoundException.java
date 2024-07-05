package giovanni.Event_Management.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Record with id " + id + " not found!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}