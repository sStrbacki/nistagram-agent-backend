package rs.ac.uns.ftn.nistagram.auth.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "permissions")
public class Permission implements GrantedAuthority {

    @Id
    String id;

    @Override
    public String getAuthority() {
        return id;
    }
}
