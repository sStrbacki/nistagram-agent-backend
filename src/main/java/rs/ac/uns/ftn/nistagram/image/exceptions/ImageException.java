package rs.ac.uns.ftn.nistagram.image.exceptions;

import rs.ac.uns.ftn.nistagram.exceptions.ApplicationException;

public class ImageException extends ApplicationException {
    public ImageException() {
        super();
    }

    public ImageException(String message) {
        super(message);
    }
}
