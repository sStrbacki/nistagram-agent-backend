package rs.ac.uns.ftn.nistagram.mail;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.nistagram.auth.user.User;

public class ActivationMessage extends EmailMessage<User>{

    public ActivationMessage(JavaMailSender mailSender, User user) {
        super(mailSender, user);
    }

    private static final String ROOT_URL = "http://localhost:8080/api/users/activate/";

    @Override
    protected String formatMessage(User user) {
        return
                "Hello, " + user.getFullName() + " ,\n"
                        + "\nPlease click the link below to activate your account:\n"
                        + ROOT_URL + user.getUuid();
    }
}
