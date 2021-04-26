package rs.ac.uns.ftn.nistagram.exceptions;


public class EntityNotFoundException extends ApplicationException {
    public EntityNotFoundException() {
        super("Entity not found.");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
