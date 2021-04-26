package rs.ac.uns.ftn.nistagram.auth.model;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCart;
import rs.ac.uns.ftn.nistagram.domain.invoice.InvoiceCollection;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String username;

    // TODO Make this unique
    private String email;

    private String fullName;

    private String passwordHash;

    @Type(type = "uuid-char")
    private UUID uuid;

    private String role;

    private boolean activated;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private ShoppingCart shoppingCart;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private InvoiceCollection invoices;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        throw new RuntimeException("Password is not available in its pure form.");
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isActivated() {
        return activated;
    }

    public InvoiceCollection getInvoices() {
        return invoices;
    }

    public void setInvoices(InvoiceCollection invoices) {
        this.invoices = invoices;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public ShoppingCart getShoppingCart() {
        if(shoppingCart.isEmpty())
            return new ShoppingCart(this);

        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void activate(){
        if(!activated)
            activated = true;
    }
}
