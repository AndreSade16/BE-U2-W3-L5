package andreasaderi.L5.controllers;

import andreasaderi.L5.entities.Reservation;
import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.ValidationException;
import andreasaderi.L5.payloads.ReservationDTO;
import andreasaderi.L5.payloads.ReservationSavedDTO;
import andreasaderi.L5.services.ReservationService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationSavedDTO save(@RequestBody @Validated ReservationDTO body, BindingResult validationResult, @AuthenticationPrincipal User user) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }
        Reservation saved = reservationService.save(body, user);

        return new ReservationSavedDTO(saved.getReservationId());
    }

    @GetMapping
    public Page<Reservation> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return reservationService.findAll(page, size);
    }

    @GetMapping("/me")
    public Page<Reservation> findOwnReservations(@AuthenticationPrincipal User user, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return reservationService.findOwnReservations(user, page, size);
    }
}
