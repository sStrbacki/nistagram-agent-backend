package rs.ac.uns.ftn.nistagram.auth.exceptions;

import rs.ac.uns.ftn.nistagram.exceptions.ApplicationException;

public class InvalidLoginCredentialsException extends ApplicationException {
    public InvalidLoginCredentialsException() {
        super("Invalid login credentials.");
    }
}
