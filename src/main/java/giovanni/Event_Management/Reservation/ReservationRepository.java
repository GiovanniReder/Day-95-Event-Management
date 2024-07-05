package giovanni.Event_Management.Reservation;

import giovanni.Event_Management.Event.Event;
import giovanni.Event_Management.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    int countByEvent(Event event);
    List<Reservation> findByUser(User user);
}
