package andreasaderi.L5.services;

import andreasaderi.L5.entities.Event;
import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.EventAlreadyExistsException;
import andreasaderi.L5.exceptions.NotFoundException;
import andreasaderi.L5.payloads.EventDTO;
import andreasaderi.L5.repositories.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public Page<Event> findAll(int page, int size) {
        if (size <= 0) size = 10;
        if (size > 20) size = 20;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size);
        return eventRepository.findAll(pageable);
    }

    public Event findById(UUID eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
    }
}
