package andreasaderi.L5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    @Setter(AccessLevel.NONE)
    private UUID reservationId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @Column(nullable = false, name = "reservation_date")
    private LocalDate reservationDate;

    public Reservation(User user, Event event) {
        this.user = user;
        this.event = event;
        this.reservationDate = LocalDate.now();
    }
}
