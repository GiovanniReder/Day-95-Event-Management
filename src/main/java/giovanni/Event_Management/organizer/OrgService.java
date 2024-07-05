package giovanni.Event_Management.organizer;

import giovanni.Event_Management.User.User;
import giovanni.Event_Management.User.UserLoginDTO;
import giovanni.Event_Management.User.UsersService;
import giovanni.Event_Management.exceptions.UnauthorizedException;
import giovanni.Event_Management.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){

        User user = this.usersService.findByEmail(payload.email());
        if(user.getPassword().equals(payload.password())){
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non corrette!");
        }
    }
}
