package giovanni.Event_Management.User;

import giovanni.Event_Management.exceptions.UnauthorizedException;
import giovanni.Event_Management.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    UsersService usersService;
    @Autowired
    PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){


        User user = this.usersService.findByEmail(payload.email());

        if((bcrypt.matches(payload.password() , user.getPassword()))){

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credentials are not correct!");
        }
    }

}
