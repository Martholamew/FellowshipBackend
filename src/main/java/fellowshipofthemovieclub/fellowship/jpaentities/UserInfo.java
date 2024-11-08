package fellowshipofthemovieclub.fellowship.jpaentities;

import com.fasterxml.jackson.annotation.*;
import fellowshipofthemovieclub.fellowship.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")  // Handle cycles using IDs
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public UserInfo(){}

    public UserInfo(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public UserInfo(UserDTO user, String hashedPassword) {
        this.password = hashedPassword;
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @JsonManagedReference  // Serializes the forward reference (roles in UserInfo)
    private List<Role> roles = new ArrayList<>();
}
