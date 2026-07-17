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
    boolean existsByTripTripIdAndEmployeeEmployeeId(UUID tripId, UUID employeeId);

    boolean existsByEmployeeEmployeeIdAndTripDate(UUID employeeId, LocalDate date);

    Page<Reservation> findByUserEmployeeId(UUID employeeId, Pageable pageable);
}
