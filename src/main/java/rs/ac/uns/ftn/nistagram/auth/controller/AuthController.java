package rs.ac.uns.ftn.nistagram.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.nistagram.auth.controller.dto.LoginDTO;
import rs.ac.uns.ftn.nistagram.auth.service.UserService;

@Controller
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        String jwt = userService.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
