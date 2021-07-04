package rs.ac.uns.ftn.nistagram.auth.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permission implements GrantedAuthority {

    @Id
    String id;

    @Override
    public String getAuthority() {
        return id;
    }
}
