package rs.ac.uns.ftn.nistagram.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.PasswordHandler;
import rs.ac.uns.ftn.nistagram.auth.user.User;
import rs.ac.uns.ftn.nistagram.auth.user.UserRepository;
import rs.ac.uns.ftn.nistagram.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.nistagram.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.nistagram.mail.EmailService;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordHandler passwordHandler;
    private final EmailService emailService;

    public UserService(UserRepository repository, PasswordHandler passwordHandler, EmailService emailService) {

        this.repository = repository;
        this.passwordHandler = passwordHandler;
        this.emailService = emailService;
    }

    public void register(User user, String password) {
        if(repository.existsById(user.getUsername()))
            throw new EntityAlreadyExistsException("User with provided username already exist.");

        configureUser(user, password);
        repository.save(user);
        emailService.sendActivationMessage(user);
    }

    private void configureUser(User user, String password) {
        user.setPasswordHash(passwordHandler.hash(password));
        user.setUuid(UUID.randomUUID());
    }

    public void activate(String uuid) {
        var user = repository.find(uuid);
        if(user == null)
            throw new EntityNotFoundException();
        user.activate();
        repository.save(user);
    }
}
