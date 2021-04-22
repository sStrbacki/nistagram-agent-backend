package rs.ac.uns.ftn.nistagram.controllers.DTOs;

import rs.ac.uns.ftn.nistagram.constraints.EmailConstraint;
import rs.ac.uns.ftn.nistagram.constraints.FullnameConstraint;
import rs.ac.uns.ftn.nistagram.constraints.PasswordConstraint;
import rs.ac.uns.ftn.nistagram.constraints.UsernameConstraint;

public class RegistrationRequestDTO {

    @UsernameConstraint
    private String username;
    @FullnameConstraint
    private String fullName;
    @EmailConstraint
    private String email;
    @PasswordConstraint
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
