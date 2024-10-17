package fellowshipofthemovieclub.fellowship.dtos;

public class AddedUser {

    public AddedUser(String name, String email, long id, boolean userAdded) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.userAdded = userAdded;
    }

    public AddedUser(boolean userAdded){
        this.userAdded=userAdded;
    }

    private String name;
    private String email;
    private long id;
    private boolean userAdded;
}
