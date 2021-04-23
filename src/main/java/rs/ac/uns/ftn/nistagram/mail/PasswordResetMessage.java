package rs.ac.uns.ftn.nistagram.mail;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.nistagram.auth.model.PasswordResetForm;

public class PasswordResetMessage extends EmailMessage<PasswordResetForm> {

    public PasswordResetMessage(JavaMailSender mailSender, PasswordResetForm entity) {
        super(mailSender, entity);
    }

    private static final String URL = EmailService.ROOT_URL + "users/password-reset/";

    @Override
    protected String formatMessage(PasswordResetForm passResetForm) {
        return
                "Hello, seems like you requested a password change.\n" +
                "If this wasn't you, please ignore this message.\n" +
                "However, if this was you, please click the link below in order to reset your password." +
                "\nThe link expires in 10 minutes.\n" +
                URL + passResetForm.getUserUUID() + "&" + passResetForm.getResetUUID();
    }
}
