package andreasaderi.L5.controllers;

import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.ValidationException;
import andreasaderi.L5.payloads.UserDTO;
import andreasaderi.L5.payloads.UserSavedDTO;
import andreasaderi.L5.services.UserService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserSavedDTO save(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }

        User saved = userService.save(body);
        return new UserSavedDTO(saved.getUserId());
    }
}
