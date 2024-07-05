package giovanni.Event_Management.Event;

import giovanni.Event_Management.User.NewUserDTO;
import giovanni.Event_Management.User.User;
import giovanni.Event_Management.User.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UsersRepository userRepository;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event, NewUserDTO body) {
        User organizer = userRepository.findByEmail(body.email()).orElseThrow();
        event.setOrganizer(organizer);
        Event createdEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails, Principal principal) {
        Event event = eventRepository.findById(id).orElseThrow();
        if (!event.getOrganizer().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        event.setPlace(eventDetails.getPlace());
        event.setMaxGuest(eventDetails.getMaxGuest());
        Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }

 */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id, Principal principal) {
        Event event = eventRepository.findById(id).orElseThrow();
        if (!event.getOrganizer().getName().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        eventRepository.delete(event);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(event);
    }
}
