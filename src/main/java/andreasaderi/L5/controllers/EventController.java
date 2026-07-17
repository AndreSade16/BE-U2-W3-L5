package andreasaderi.L5.controllers;

import andreasaderi.L5.entities.Event;
import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.ValidationException;
import andreasaderi.L5.payloads.EventDTO;
import andreasaderi.L5.payloads.EventSavedDTO;
import andreasaderi.L5.services.EventService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventSavedDTO save(@RequestBody @Validated EventDTO body, BindingResult validationResult, @AuthenticationPrincipal User user) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }
        Event saved = eventService.save(body, user);

        return new EventSavedDTO(saved.getEventId());
    }


    @GetMapping
    public Page<Event> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return eventService.findAll(page, size);
    }

    public Event findById(UUID eventId) {
        return eventService.findById(eventId);
    }
}
