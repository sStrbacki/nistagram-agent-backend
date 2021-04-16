package rs.ac.uns.ftn.nistagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.nistagram.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
