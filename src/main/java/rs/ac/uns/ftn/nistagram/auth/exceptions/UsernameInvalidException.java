package rs.ac.uns.ftn.nistagram.auth.exceptions;

import rs.ac.uns.ftn.nistagram.exceptions.ApplicationException;

public class UsernameInvalidException extends ApplicationException {
    public UsernameInvalidException() {
        super("Username invalid");
    }
}
