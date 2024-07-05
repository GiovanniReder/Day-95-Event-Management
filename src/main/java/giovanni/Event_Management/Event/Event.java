package giovanni.Event_Management.Event;

import giovanni.Event_Management.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "event")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime date;
    private String place;
    private int maxGuest;

    @ManyToOne
    private User organizer;


}

