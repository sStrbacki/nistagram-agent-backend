package rs.ac.uns.ftn.nistagram.exceptions;

import javax.persistence.PersistenceException;

public class EntityNotFoundException extends PersistenceException {
    public EntityNotFoundException() {
        super("Entity not found.");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
