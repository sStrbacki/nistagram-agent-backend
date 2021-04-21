package rs.ac.uns.ftn.nistagram.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.nistagram.auth.controller.dto.LoginDTO;
import rs.ac.uns.ftn.nistagram.auth.identity.IdentityService;

@Controller
@RequestMapping("api/auth")
public class AuthController {

    private final IdentityService identityService;

    public AuthController(IdentityService identityService) {
        this.identityService = identityService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(LoginDTO dto) {
        identityService.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok().build();
    }
}
