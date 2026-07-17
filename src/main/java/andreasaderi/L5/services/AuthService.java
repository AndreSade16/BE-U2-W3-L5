package andreasaderi.L5.services;

import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.UnauthorizedException;
import andreasaderi.L5.payloads.LoginDTO;
import andreasaderi.L5.payloads.LoginResponseDTO;
import andreasaderi.L5.security.JWTTools;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JWTTools jwtTools;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JWTTools jwtTools, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtTools = jwtTools;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO checkLoginAndGenerateToken(LoginDTO body) {
        User user = userService.findByEmail(body.email());
        if (!passwordEncoder.matches(body.password(), user.getPassword()))
            throw new UnauthorizedException("Wrong credentials.");
        String token = jwtTools.generateToken(user);
        return new LoginResponseDTO(token);
    }
}
