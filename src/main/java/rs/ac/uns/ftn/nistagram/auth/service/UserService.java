package rs.ac.uns.ftn.nistagram.auth.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.exceptions.InvalidLoginCredentialsException;
import rs.ac.uns.ftn.nistagram.auth.exceptions.UsernameInvalidException;
import rs.ac.uns.ftn.nistagram.auth.model.IdentityToken;
import rs.ac.uns.ftn.nistagram.auth.model.User;
import rs.ac.uns.ftn.nistagram.auth.repository.UserRepository;
import rs.ac.uns.ftn.nistagram.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.nistagram.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.nistagram.mail.EmailService;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHandler passwordHandler;
    private final JwtService jwtService;
    private final EmailService emailService;

    public UserService(
            UserRepository userRepository,
            PasswordHandler passwordHandler,
            EmailService emailService,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordHandler = passwordHandler;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.getByUsername(username);
        if (optionalUser.isEmpty()) throw new UsernameInvalidException();
        User user = optionalUser.get();

        boolean success = passwordHandler.checkPass(password, user.getPasswordHash());
        if (!success) throw new InvalidLoginCredentialsException();

        return jwtService.encrypt(new IdentityToken(user));
    }

    public void register(User user, String password) {
        if(userRepository.existsById(user.getUsername()))
            throw new EntityAlreadyExistsException("User with provided username already exist.");

        configureUser(user, password);
        userRepository.save(user);
        emailService.sendActivationMessage(user);
    }

    private void configureUser(User user, String password) {
        user.setPasswordHash(passwordHandler.hash(password));
        user.setUuid(UUID.randomUUID());
    }

    public void activate(String uuid) {
        var user = userRepository.find(uuid);
        if(user == null)
            throw new EntityNotFoundException();
        user.activate();
        userRepository.save(user);
    }
}
