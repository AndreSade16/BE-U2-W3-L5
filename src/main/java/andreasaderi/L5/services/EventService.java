package andreasaderi.L5.services;

import andreasaderi.L5.entities.Event;
import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.EventAlreadyExistsException;
import andreasaderi.L5.payloads.EventDTO;
import andreasaderi.L5.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(EventDTO body, User user) {
        if (eventRepository.existsByLocationIgnoreCaseAndDate(body.location(), body.date()))
            throw new EventAlreadyExistsException(body.location(), body.date());
        return eventRepository.save(new Event(body.title(), body.description(), body.date(), body.location(), body.allowance(), user));
    }
}
