package fellowshipofthemovieclub.fellowship.dtos;

import lombok.Data;

@Data
public class UserDTO {

    public UserDTO(long userId) {
        this.userId = userId;
    }

    public UserDTO() {
    }

    private long userId;
    private String userName;
    private String email;
    private String password;
    private String message;// we shouldnt really be using this here so that we can keep the dto only for user info.
    // Maybe look at extending our DTOS with an error handling DTO


    public UserDTO(long userId, String name, String email, String message) {
        this.userId = userId;
        this.userName = name;
        this.email = email;
        this.message = message;
    }

    public UserDTO(long id, String name, String email) {
        this.userId=id;
        this.userName =name;
        this.email=email;
    }
    public UserDTO(String message) {
        this.message = message;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
