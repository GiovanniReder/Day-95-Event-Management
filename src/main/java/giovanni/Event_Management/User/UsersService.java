package giovanni.Event_Management.User;

import giovanni.Event_Management.User.User;
import giovanni.Event_Management.User.UsersRepository;
import giovanni.Event_Management.exceptions.BadRequestException;
import giovanni.Event_Management.exceptions.NotFoundException;
import io.jsonwebtoken.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder bcrypt;

public UserTypeEnum role(NewUserDTO body){
    try {
        return UserTypeEnum.valueOf(body.role().toUpperCase());
    }
     catch(IllegalArgumentException ex){
        throw new BadRequestException("Role must be user or organizer");
     }

}

    public User save(NewUserDTO body) {
        this.usersRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );

        User newUser = new User(body.name(), body.surname(), body.email(), bcrypt.encode(body.password()) , role(body) );



        return usersRepository.save(newUser);
    }

    public User findById(long userId) {
        return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User getUser(long id){
        Optional<User> user = usersRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        } else {
            throw new NotFoundException("User no found!");
        }
    }




    public User findByEmail(String email){
        return usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }

}