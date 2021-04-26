package rs.ac.uns.ftn.nistagram.auth.exceptions;

import rs.ac.uns.ftn.nistagram.exceptions.ApplicationException;

public class UserNotActivatedException extends ApplicationException {
    public UserNotActivatedException() {
        super("Your account has not been activated yet.");
    }
}
