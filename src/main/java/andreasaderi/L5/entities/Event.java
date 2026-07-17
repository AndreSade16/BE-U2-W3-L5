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
@Table(name = "events")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue
    @Column(name = "event_id")
    @Setter(AccessLevel.NONE)
    private UUID eventId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int allowance;
    @ManyToOne
    @JoinColumn(name = "organizator_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private User organizer;

    public Event(String title, String description, LocalDate date, String location, int allowance, User organizer) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.allowance = allowance;
        this.organizer = organizer;
    }
}
