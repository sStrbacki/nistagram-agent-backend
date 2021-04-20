package rs.ac.uns.ftn.nistagram.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.auth.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.username = ?1")
    Optional<User> getByUsername(String username);
}
