package Api.de.Login.Api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Api.de.Login.Api.dto.LoginRequest;
import Api.de.Login.Api.model.Usuario;
import Api.de.Login.Api.service.AuthService; 
import Api.de.Login.Api.service.JwtService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;   

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Usuario user = authService.login(request.getEmail(), request.getPassword());

        if (user == null) {
            return ResponseEntity.status(401).body(
                Map.of("message", "Credenciales incorrectas")
            );
        }

        String token = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(
            Map.of(
                "message", "Login exitoso",
                "token", token,                           
                "usuario", Map.of(
                    "id", user.getId(),
                    "nombre", user.getNombre(),
                    "email", user.getEmail()
                )
            )
        );
    }
}
