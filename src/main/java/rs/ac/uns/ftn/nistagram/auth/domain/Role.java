package rs.ac.uns.ftn.nistagram.auth.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    private String id;
    @ManyToMany(fetch = FetchType.EAGER)
    private final List<Permission> allowedPermissions = new ArrayList<>();
    public List<Permission> getAllowedPermissions() {
        return allowedPermissions;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
