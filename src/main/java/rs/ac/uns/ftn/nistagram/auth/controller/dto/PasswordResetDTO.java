package rs.ac.uns.ftn.nistagram.auth.controller.dto;

import rs.ac.uns.ftn.nistagram.constraints.PasswordConstraint;

public class PasswordResetDTO {
    @PasswordConstraint
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
