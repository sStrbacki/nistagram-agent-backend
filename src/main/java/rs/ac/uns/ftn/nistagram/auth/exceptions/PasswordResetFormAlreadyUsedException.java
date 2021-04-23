package rs.ac.uns.ftn.nistagram.auth.exceptions;

import rs.ac.uns.ftn.nistagram.exceptions.ApplicationException;

public class PasswordResetFormAlreadyUsedException extends ApplicationException {
    public PasswordResetFormAlreadyUsedException() {
        super("This password recovery link has already been used. Please request a new one");
    }
}
