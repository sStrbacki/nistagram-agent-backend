package rs.ac.uns.ftn.nistagram.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.model.PasswordResetForm;
import rs.ac.uns.ftn.nistagram.auth.model.User;

import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private static final String FRONT_PORT = "8080";
    private static final String BACK_PORT = "4000";

    static final String FRONT_ROOT_URL = "http://localhost:" + FRONT_PORT + "/";
    static final String BACK_ROOT_URL = "https://localhost:" + BACK_PORT + "/api/";

    private static final String SENDER_EMAIL = "nistagram.info@gmail.com";


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivationMessage(User user) {
        var message = new ActivationMessage(mailSender, user);
        message.send(SENDER_EMAIL, user.getEmail(), "Activate your account");
    }

    public void sendPasswordResetMessage(String email, PasswordResetForm passwordResetForm) {
        PasswordResetMessage message = new PasswordResetMessage(mailSender, passwordResetForm);
        message.send(SENDER_EMAIL, email, "[NISTAGRAM] Password reset requested");
    }
}
