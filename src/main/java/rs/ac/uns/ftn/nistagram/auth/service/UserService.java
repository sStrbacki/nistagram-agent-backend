package rs.ac.uns.ftn.nistagram.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.exceptions.InvalidLoginCredentialsException;
import rs.ac.uns.ftn.nistagram.auth.exceptions.UsernameInvalidException;
import rs.ac.uns.ftn.nistagram.auth.model.IdentityToken;
import rs.ac.uns.ftn.nistagram.auth.model.User;
import rs.ac.uns.ftn.nistagram.auth.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHandler passwordHandler;
    private final JwtService<IdentityToken> jwtService;

    public UserService(UserRepository userRepository, PasswordHandler passwordHandler) {
        this.userRepository = userRepository;
        this.passwordHandler = passwordHandler;
        this.jwtService = new JwtService<>(IdentityToken.class);
    }

    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.getByUsername(username);
        if (optionalUser.isEmpty()) throw new UsernameInvalidException();
        User user = optionalUser.get();

        boolean success = passwordHandler.checkPass(password, user.getPasswordHash());
        if (!success) throw new InvalidLoginCredentialsException();

        return jwtService.encrypt(new IdentityToken(user));
    }
}
