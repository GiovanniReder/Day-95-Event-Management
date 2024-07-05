package giovanni.Event_Management.Reservation;

import giovanni.Event_Management.Event.Event;
import giovanni.Event_Management.User.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;


}
