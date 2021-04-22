package rs.ac.uns.ftn.nistagram.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public abstract class EmailMessage<T> {

    private final JavaMailSender mailSender;
    private final SimpleMailMessage mailMessage;
    private final T entity;

    public EmailMessage(JavaMailSender mailSender, T entity) {
        this.mailSender = mailSender;
        this.mailMessage = new SimpleMailMessage();
        this.entity = entity;
    }

    @Async
    public void send(String from, String to, String subject) {
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(formatMessage(entity));
        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    protected abstract String formatMessage(T entity);
}
