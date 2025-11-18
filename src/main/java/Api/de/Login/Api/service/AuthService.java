package Api.de.Login.Api.service;

import org.springframework.stereotype.Service;

import Api.de.Login.Api.model.Usuario;
import Api.de.Login.Api.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario login(String email, String password) {
        Usuario user = usuarioRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user; 
    }
}

