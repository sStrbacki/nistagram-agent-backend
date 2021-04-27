package rs.ac.uns.ftn.nistagram.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.nistagram.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("select role from roles role where role.id like 'ROLE_USER' ")
    Role getUserRole();
}
