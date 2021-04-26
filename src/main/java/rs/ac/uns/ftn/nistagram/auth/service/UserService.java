package rs.ac.uns.ftn.nistagram.auth.service;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.nistagram.auth.exceptions.*;
import rs.ac.uns.ftn.nistagram.auth.model.IdentityToken;
import rs.ac.uns.ftn.nistagram.auth.model.PasswordResetForm;
import rs.ac.uns.ftn.nistagram.auth.model.User;
import rs.ac.uns.ftn.nistagram.auth.repository.PasswordResetFormRepository;
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
    private final PasswordResetFormRepository passResetRepository;

    public UserService(
            UserRepository userRepository,
            PasswordHandler passwordHandler,
            EmailService emailService,
            JwtService jwtService,
            PasswordResetFormRepository passResetRepository
    ) {
        this.userRepository = userRepository;
        this.passwordHandler = passwordHandler;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.passResetRepository = passResetRepository;
    }

    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.getByUsername(username);
        if (optionalUser.isEmpty()) throw new UsernameInvalidException();
        User user = optionalUser.get();

        boolean success = passwordHandler.checkPass(password, user.getPasswordHash());
        if (!success) throw new InvalidLoginCredentialsException();

        if (!user.isActivated()) throw new UserNotActivatedException();

        return jwtService.encrypt(new IdentityToken(user));
    }

    public void register(User user, String password) {
        if(userRepository.existsById(user.getUsername()))
            throw new EntityAlreadyExistsException("User with provided username already exist.");

        user.setPasswordHash(passwordHandler.hash(password));
        user.setUuid(UUID.randomUUID());

        userRepository.save(user);
        emailService.sendActivationMessage(user);
    }

    public void activate(UUID uuid) {
        Optional<User> optionalUser = userRepository.findByUUID(uuid);
        if(optionalUser.isEmpty())
            throw new EntityNotFoundException();

        User user = optionalUser.get();

        user.activate();
        userRepository.save(user);
    }

    public void requestPasswordReset(String username) {
        User user = userRepository.findById(username).orElseThrow(EntityNotFoundException::new);
        PasswordResetForm passwordResetForm = new PasswordResetForm(user.getUuid());
        passResetRepository.save(passwordResetForm);
        emailService.sendPasswordResetMessage(user.getEmail(), passwordResetForm);
    }

    public void resetPassword(UUID userUUID, UUID resetUUID, String newPassword) {
        PasswordResetForm passwordResetForm =
                passResetRepository.findByUUIDPair(userUUID, resetUUID)
                        .orElseThrow(EntityNotFoundException::new);

        if (!passwordResetForm.isNonExpired()) throw new PasswordResetFormExpiredException();
        if (passwordResetForm.isUsed()) throw new PasswordResetFormAlreadyUsedException();

        User user = userRepository.findByUUID(userUUID).orElseThrow(EntityNotFoundException::new);
        user.setPasswordHash(passwordHandler.hash(newPassword));
        userRepository.save(user);

        passwordResetForm.setUsed(true);
        passResetRepository.save(passwordResetForm);
    }
}
