package giovanni.Event_Management.User;

import giovanni.Event_Management.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserControllerAuth {

    @Autowired
    UsersService usersService;

    @Autowired
    UserAuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO userResponseDTO (@RequestBody @Validated NewUserDTO newUserDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new NewUserResponseDTO(usersService.save(newUserDTO).getId());
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login (@RequestBody @Validated UserLoginDTO payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new UserLoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }
}
