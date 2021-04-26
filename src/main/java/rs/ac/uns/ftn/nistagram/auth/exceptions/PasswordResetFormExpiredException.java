package rs.ac.uns.ftn.nistagram.auth.exceptions;

import rs.ac.uns.ftn.nistagram.exceptions.ApplicationException;

public class PasswordResetFormExpiredException extends ApplicationException {
    public PasswordResetFormExpiredException() {
        super("Password reset form has expired. Please apply for a new one.");
    }
}
