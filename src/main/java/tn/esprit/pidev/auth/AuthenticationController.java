package tn.esprit.pidev.auth;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.Role;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repository.IUserRepository;
import tn.esprit.pidev.services.IGestionUser;
import tn.esprit.pidev.user.ChangePasswordRequest;
import tn.esprit.pidev.user.UserService;

import java.io.IOException;
import java.security.Principal;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private  HttpResponse res;

    @RestController
    @RequestMapping("/changepass")
    @RequiredArgsConstructor
    public class Controller {

        private final UserService service;

        @PatchMapping
        public ResponseEntity<AuthenticationResponse> changePassword(
                @RequestBody ChangePasswordRequest request,
                Principal connectedUser
        ) {
            service.changePassword(request, connectedUser);
            return ResponseEntity.ok().build();
        }
    }
    @GetMapping("/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            return "ghalet";
        }
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}