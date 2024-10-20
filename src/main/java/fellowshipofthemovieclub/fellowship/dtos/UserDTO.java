package fellowshipofthemovieclub.fellowship.dtos;

import lombok.Data;


public class UserDTO {

    public UserDTO(long userId) {
        this.userId = userId;
    }

    public UserDTO() {
    }

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
