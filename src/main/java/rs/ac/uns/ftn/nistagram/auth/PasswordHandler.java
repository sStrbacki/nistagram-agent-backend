package rs.ac.uns.ftn.nistagram.auth;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.nistagram.config.PasswordHandlingConfig;

@Component
public class PasswordHandler {

    private final PasswordHandlingConfig config;

    public PasswordHandler(PasswordHandlingConfig handlingConfig) {
        config = handlingConfig;
    }

    /** Generates a random salt string */
    public String generateSalt() {
        return BCrypt.gensalt(config.getLogRounds());
    }

    /** Hashes the given password string using BCrypt algorithm and provided salt string */
    public String hash(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    /** Hashes the given password string using BCrypt algorithm */
    public String hash(String password){
        var salt = BCrypt.gensalt(config.getLogRounds());
        return BCrypt.hashpw(password,salt);
    }

    /** Check whether the given password matches a hashed one */
    public boolean checkPass(String password, String passHash) {
        return BCrypt.checkpw(password, passHash);
    }

}
