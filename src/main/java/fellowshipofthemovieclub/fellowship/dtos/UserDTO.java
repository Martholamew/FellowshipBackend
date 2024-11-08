package fellowshipofthemovieclub.fellowship.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    public UserDTO(long userId) {
        this.userId = userId;
    }

    public UserDTO() {}

    private long userId;
    private String userName;
    private String email;
    private String password;
    private List<RoleDTO> roles;  // Changed from List<Role> to List<RoleDTO>
    private String message;
    private String token;

    public UserDTO(long id, String name, String email) {
        this.userId = id;
        this.userName = name;
        this.email = email;
    }

    public UserDTO(String message) {
        this.message = message;
    }

    public UserDTO(long userId, String userName, String email, String message, List<RoleDTO> roles, String token) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.message = message;
        this.roles = roles;
        this.token = token;
    }
}
