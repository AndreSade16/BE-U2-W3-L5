package andreasaderi.L5.services;

import andreasaderi.L5.entities.User;
import andreasaderi.L5.payloads.LoginDTO;
import andreasaderi.L5.payloads.LoginResponseDTO;
import andreasaderi.L5.security.JWTTools;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JWTTools jwtTools;
    private final UserService userService;

    public AuthService(JWTTools jwtTools, UserService userService) {
        this.jwtTools = jwtTools;
        this.userService = userService;
    }

    public LoginResponseDTO checkLoginAndGenerateToken(LoginDTO body) {
        User user = userService.findByEmail(body.email());
        String token = jwtTools.generateToken(user);
        return new LoginResponseDTO(token);
    }
}
