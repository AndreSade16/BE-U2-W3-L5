package andreasaderi.L5.repositories;

import andreasaderi.L5.entities.Event;
import andreasaderi.L5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    boolean existsByLocationIgnoreCaseAndDate(String location, LocalDate date);

    boolean existsByEventIdAndOrganizer(UUID eventId, User organizer);
}
