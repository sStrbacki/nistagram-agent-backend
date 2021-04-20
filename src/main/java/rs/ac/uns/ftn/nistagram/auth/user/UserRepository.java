package rs.ac.uns.ftn.nistagram.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.auth.identity.Identity;
import rs.ac.uns.ftn.nistagram.auth.identity.IdentityRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, IdentityRepository {

    @Override
    @Query("select u from User u where u.username = ?1")
    Optional<Identity> getByUsername(String username);

    @Query(value = "select * from users where uuid=:uuid", nativeQuery = true)
    User find(@Param("uuid") String uuid);
}
