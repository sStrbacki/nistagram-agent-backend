package rs.ac.uns.ftn.nistagram.auth;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;

public class Crypto {

    private final SecureRandom rng;
    private final BCryptPasswordEncoder encoder;

    public Crypto() {
        rng  = new SecureRandom();
        encoder = new BCryptPasswordEncoder();
    }

    /** Generates a random array of bytes of the given length */
    public byte[] generateSalt(int saltLength) {
        byte[] salt = new byte[saltLength];
        rng.nextBytes(salt);
        return salt;
    }

    /** Encrypts the given char sequence using Scrypt algorithm */
    public byte[] hash(CharSequence charSequence, byte[] salt) {
        String passHashString =
                encoder.encode(charSequence + Base64.encodeBase64String(salt));
        return passHashString.getBytes();
    }

    /** Check whether the given password + salt hashing result equals the given passHash */
    public boolean checkPass(String pass, byte[] salt, byte[] passHash) {
        return Arrays.equals(hash(pass, salt), passHash);
    }
}
