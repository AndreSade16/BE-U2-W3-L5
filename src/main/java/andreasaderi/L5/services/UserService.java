package andreasaderi.L5.services;

import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.EmailAlreadyInUseException;
import andreasaderi.L5.exceptions.EmailNotFoundException;
import andreasaderi.L5.exceptions.NotFoundException;
import andreasaderi.L5.payloads.UserDTO;
import andreasaderi.L5.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(UserDTO body) {
        if (userRepository.existsByEmail(body.email())) throw new EmailAlreadyInUseException(body.email());

        return userRepository.save(new User(body.name(), body.surname(), body.email(), passwordEncoder.encode(body.password())));
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException(email));
    }
}
