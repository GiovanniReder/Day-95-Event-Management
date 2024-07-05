package giovanni.Event_Management.User;

import giovanni.Event_Management.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class
RegistrationController {
    @Autowired
    UsersService userService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO newUserResponseDTO  (@RequestBody @Validated NewUserDTO userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new NewUserResponseDTO(userService.save(userDto).getId());
    }

}
