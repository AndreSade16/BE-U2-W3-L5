package andreasaderi.L5.services;

import andreasaderi.L5.entities.Event;
import andreasaderi.L5.entities.Reservation;
import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.ReservationAlreadyExistsException;
import andreasaderi.L5.payloads.ReservationDTO;
import andreasaderi.L5.repositories.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final EventService eventService;
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository, EventService eventService) {
        this.reservationRepository = reservationRepository;
        this.eventService = eventService;
    }


    public Reservation save(ReservationDTO body, User user) {
        if (reservationRepository.existsByEventEventIdAndUserUserId(body.eventId(), user.getUserId()))
            throw new ReservationAlreadyExistsException(body.eventId(), user);
        Event event = eventService.findById(body.eventId());
        return reservationRepository.save(new Reservation(user, event));
    }

    public Page<Reservation> findAll(int page, int size) {
        if (size <= 0) size = 10;
        if (size > 20) size = 20;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size);
        return reservationRepository.findAll(pageable);
    }
}
