package rs.ac.uns.ftn.nistagram.auth.identity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.middle.AuthorizationException;
import rs.ac.uns.ftn.nistagram.auth.user.UserRepository;

@Service
public class IdentityService implements UserDetailsService {

    private final IdentityRepository identityRepository;

    public IdentityService(UserRepository userRepository) {
        this.identityRepository = userRepository;
    }

    @Override
    public Identity loadUserByUsername(String username) throws UsernameNotFoundException {
        return identityRepository.getByUsername(username)
                .orElseThrow(AuthorizationException::new);
    }

    public void login(String username, String password) {

    }
}
