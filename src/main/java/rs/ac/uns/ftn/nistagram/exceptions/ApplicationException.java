package rs.ac.uns.ftn.nistagram.exceptions;

public class ApplicationException extends RuntimeException {
    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }
}
