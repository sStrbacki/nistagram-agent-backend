package rs.ac.uns.ftn.nistagram.auth.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordHandler {

    /** Encrypts the given char sequence using BCrypt algorithm */
    public String hash(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt(6));
    }

    /** Check whether the given password hash belongs to the given password */
    public boolean checkPass(String pass, String passHash) {
        return BCrypt.checkpw(pass.getBytes(), passHash);
    }
}
