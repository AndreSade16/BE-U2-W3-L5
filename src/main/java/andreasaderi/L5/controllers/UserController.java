package andreasaderi.L5.controllers;

import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.ValidationException;
import andreasaderi.L5.payloads.SetUserRoleDTO;
import andreasaderi.L5.services.UserService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<User> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.findAll(page, size);
    }

    @PatchMapping("/roles/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public User setUserRole(@RequestBody @Validated SetUserRoleDTO body, BindingResult validationResult, @PathVariable UUID userId) {
        if (validationResult.hasErrors())
            throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        return userService.setUserRole(body, userId);
    }

}
