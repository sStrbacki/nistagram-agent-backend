package rs.ac.uns.ftn.nistagram.auth.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.nistagram.auth.controller.dto.PasswordResetDTO;
import rs.ac.uns.ftn.nistagram.auth.controller.dto.RegistrationRequestDTO;
import rs.ac.uns.ftn.nistagram.auth.middle.HttpUtil;
import rs.ac.uns.ftn.nistagram.auth.model.User;
import rs.ac.uns.ftn.nistagram.auth.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final ModelMapper mapper;
    private final UserService userService;

    public UserController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequestDTO registrationRequest) {
        userService.register(mapper.map(registrationRequest, User.class), registrationRequest.getPassword());
        return ResponseEntity.ok("User successfully registered");
    }

    @GetMapping("/activate/{uuid}")
    public ResponseEntity<String> activate(@PathVariable UUID uuid) {
        userService.activate(uuid);
        return ResponseEntity.ok("Your account is activated. You may now log in.");
    }

    @GetMapping("request-password-reset/{username}")
    public ResponseEntity<?> requestPasswordReset(@PathVariable String username) {
        userService.requestPasswordReset(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("password-reset/{userUUID}&{resetUUID}")
    public ResponseEntity<?> resetPassword(
            @RequestBody @Valid PasswordResetDTO passwordResetDTO,
            @PathVariable UUID userUUID,
            @PathVariable UUID resetUUID
    ){
        userService.resetPassword(userUUID, resetUUID, passwordResetDTO.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("role")
    public ResponseEntity<List<String>> getRole(HttpServletRequest request) {
        String username = (String) HttpUtil.getHttpRequestAttribute(request, HttpUtil.USERNAME_KEY);
        return ResponseEntity.ok(userService.getRoles(username));
    }
}
