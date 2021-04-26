package rs.ac.uns.ftn.nistagram.mail;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.nistagram.auth.model.User;

public class ActivationMessage extends EmailMessage<User> {


    public ActivationMessage(JavaMailSender mailSender, User user) {
        super(mailSender, user);
    }

    private static final String URL = EmailService.BACK_ROOT_URL + "users/activate/";

    @Override
    protected String formatMessage(User user) {
        return
                "Hello, " + user.getFullName() + " ,\n"
                        + "\nPlease click the link below to activate your account:\n"
                        + URL + user.getUuid();
    }
}
