package rs.ac.uns.ftn.nistagram.auth.identity;

import java.util.Optional;

public interface IdentityRepository {

    Optional<Identity> getByUsername(String username);
    Optional<Identity> getByEmail(String email);
}
