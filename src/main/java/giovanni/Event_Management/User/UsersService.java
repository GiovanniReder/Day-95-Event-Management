package giovanni.Event_Management.User;

import giovanni.Event_Management.exceptions.BadRequestException;
import giovanni.Event_Management.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Page<User> getUsers(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 100) pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return usersRepository.findAll(pageable);
    }

    public User save(NewUserDTO body) {

        this.usersRepository.findByEmail(body.email()).ifPresent(

                user -> {
                    throw new BadRequestException("The mail " + body.email() + " is already used!");
                }
        );

        User newUser = new User(body.name(), body.surname(), body.email(), body.password() );




        return usersRepository.save(newUser);
    }

    public User findById(long userId) {
        return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByIdAndUpdate(long userId, User modifiedUser) {
        User found = this.findById(userId);
        found.setName(modifiedUser.getName());
        found.setSurname(modifiedUser.getSurname());
        found.setEmail(modifiedUser.getEmail());
        found.setPassword(modifiedUser.getPassword());

        return this.usersRepository.save(found);
    }

    public void findByIdAndDelete(long userId) {
        User found = this.findById(userId);
        this.usersRepository.delete(found);
    }

    public User findByEmail(String email){
        return usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email " + email + " not found!"));
    }

}
