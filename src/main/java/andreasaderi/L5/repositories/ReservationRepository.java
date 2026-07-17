package andreasaderi.L5.repositories;

import andreasaderi.L5.entities.Event;
import andreasaderi.L5.entities.Reservation;
import andreasaderi.L5.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    boolean existsByEventEventIdAndUserUserId(UUID eventId, UUID userId);

    boolean existsByUserUserIdAndEventDate(UUID userId, LocalDate date);

    Page<Reservation> findByUser(User userId, Pageable pageable);

    List<Reservation> findByEvent(Event event);
}
