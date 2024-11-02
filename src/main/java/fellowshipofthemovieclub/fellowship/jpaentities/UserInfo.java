package fellowshipofthemovieclub.fellowship.jpaentities;

import fellowshipofthemovieclub.fellowship.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
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

    public  UserInfo(){}

    public UserInfo(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public UserInfo(UserDTO user, String hashedPassword) {
        this.password = hashedPassword;
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}

