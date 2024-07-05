package giovanni.Event_Management.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(



        @NotEmpty(message = "The email is mandatory data!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email is not valid")
        String email,

        @NotEmpty(message = "The password is mandatory data!")
        @Size(min = 3, max = 25, message = "The password must be between 3 and 25 characters!")
        String password






) {
}
