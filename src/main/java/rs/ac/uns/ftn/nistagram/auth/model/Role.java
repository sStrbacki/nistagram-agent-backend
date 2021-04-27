package rs.ac.uns.ftn.nistagram.auth.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private String id;

    @ManyToMany(fetch = FetchType.EAGER)
    private final List<Permission> allowedPermissions = new ArrayList<>();

    @Override
    public String getAuthority() {
        return id;
    }

    public List<Permission> getAllowedPermissions() {
        return allowedPermissions;
    }
}
