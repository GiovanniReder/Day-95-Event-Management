package giovanni.Event_Management.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String surname;
    private String email;
    private String password;
@Enumerated(EnumType.STRING)
private UserTypeEnum type;


    public User(String name, String surname, String email, String password) {
    }
}
