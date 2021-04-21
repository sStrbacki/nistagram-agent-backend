package rs.ac.uns.ftn.nistagram.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.user.User;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private static final String SENDER_EMAIL = "nistagram.info@gmail.com";

    public void sendActivationMessage(User user) {
        var message = new ActivationMessage(mailSender, user);
        message.send(SENDER_EMAIL, user.getEmail(), "Activate your account");
    }

}
