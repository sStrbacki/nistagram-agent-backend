package rs.ac.uns.ftn.nistagram.auth.identity;

import org.springframework.security.core.userdetails.UserDetails;

/** Interface which encapsulates the default identity functionalities within a templated system */
public interface Identity extends UserDetails {
    String getUsername();
    String getEmail();
}
