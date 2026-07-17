package andreasaderi.L5.repositories;

import andreasaderi.L5.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    boolean existsByEventEventIdAndUserUserId(UUID eventId, UUID userId);

    boolean existsByUserUserIdAndEventDate(UUID userId, LocalDate date);

    Page<Reservation> findByUserUserId(UUID userId, Pageable pageable);
}
