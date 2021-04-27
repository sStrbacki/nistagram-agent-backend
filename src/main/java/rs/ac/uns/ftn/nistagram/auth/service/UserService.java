package rs.ac.uns.ftn.nistagram.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.exceptions.*;
import rs.ac.uns.ftn.nistagram.auth.model.PasswordResetForm;
import rs.ac.uns.ftn.nistagram.auth.model.Role;
import rs.ac.uns.ftn.nistagram.auth.model.User;
import rs.ac.uns.ftn.nistagram.auth.repository.PasswordResetFormRepository;
import rs.ac.uns.ftn.nistagram.auth.repository.RoleRepository;
import rs.ac.uns.ftn.nistagram.auth.repository.UserRepository;
import rs.ac.uns.ftn.nistagram.exceptions.EntityAlreadyExistsException;
import rs.ac.uns.ftn.nistagram.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.nistagram.mail.EmailService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHandler passwordHandler;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final PasswordResetFormRepository passResetRepository;
    private final RoleRepository roleRepository;

    public UserService(
            UserRepository userRepository,
            PasswordHandler passwordHandler,
            EmailService emailService,
            JwtService jwtService,
            PasswordResetFormRepository passResetRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordHandler = passwordHandler;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.passResetRepository = passResetRepository;
        this.roleRepository = roleRepository;
    }

    public User get(String username){
        Optional<User> optionalUser = userRepository.getByUsername(username);
        if (optionalUser.isEmpty()) throw new InvalidLoginCredentialsException();
        var user = optionalUser.get();

        if (!user.isActivated()) throw new UserNotActivatedException();

        return user;

    }
    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.getByUsername(username);
        if (optionalUser.isEmpty()) throw new InvalidLoginCredentialsException();
        User user = optionalUser.get();

        boolean success = passwordHandler.checkPass(password, user.getPasswordHash());
        if (!success) throw new InvalidLoginCredentialsException();

        if (!user.isActivated()) throw new UserNotActivatedException();

        return jwtService.encrypt(user.getUsername());
    }

    public void register(User user, String password) {
        if(userRepository.existsById(user.getUsername()))
            throw new EntityAlreadyExistsException("User with provided username already exist.");

        user.setPasswordHash(passwordHandler.hash(password));
        user.setUuid(UUID.randomUUID());
        user.addRoles(roleRepository.getUserRole());

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

    public List<String> getRoles(String username) {
        User user = userRepository.getByUsername(username).orElseThrow(EntityAlreadyExistsException::new);
        return user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toList());
    }
}
