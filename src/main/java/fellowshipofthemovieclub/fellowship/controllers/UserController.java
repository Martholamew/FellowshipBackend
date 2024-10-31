package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import fellowshipofthemovieclub.fellowship.repositories.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//don't leave this as all
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //unused
    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/userslogin")
    UserInfo loginUser(@RequestBody UserInfo user) {
        return userRepository.findFirstByNameAndEmail(user.getName(), user.getEmail());
    }

    @PostMapping(value="/userssignup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    boolean addUser(@RequestBody UserInfo user) {
        user.setPassword("thisisaplaceholder");
        if(loginUser(user)==null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
