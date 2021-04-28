package rs.ac.uns.ftn.nistagram.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.auth.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("select role from roles role where role.id like 'ROLE_USER' ")
    Role getUserRole();
}
