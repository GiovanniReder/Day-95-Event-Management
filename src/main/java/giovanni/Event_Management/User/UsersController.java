package giovanni.Event_Management.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


// save utente
    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId) {
        return this.usersService.findById(userId);
    }
/*
    @PutMapping("/{userId}")
    public User findByIdAndUpdate(@PathVariable long userId, @RequestBody User body) {
        return this.usersService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long userId) {
        this.usersService.findByIdAndDelete(userId);
    }

 */

}
