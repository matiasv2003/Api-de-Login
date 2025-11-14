package Api.de.Login.Api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Api.de.Login.Api.dto.LoginRequest;
import Api.de.Login.Api.service.AuthService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        boolean ok = authService.login(request.getEmail(), request.getPassword());

        if (!ok) {
            return ResponseEntity.status(401).body(
                    Map.of("message", "Credenciales incorrectas")
            );
        }

        return ResponseEntity.ok(
                Map.of("message", "Login exitoso")
        );
    }
}
