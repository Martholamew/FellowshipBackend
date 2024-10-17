package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.dtos.AddedUser;
import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import fellowshipofthemovieclub.fellowship.repositories.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//dont leave this as all
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        return (List<UserInfo>) userRepository.findAll();
    }

    @PostMapping("/userslogin")
    boolean loginUser(@RequestBody UserInfo user) {
       UserInfo validUser= userRepository.findFirstByNameAndEmail(user.getName(), user.getEmail());
        return validUser!=null;
    }

    @PostMapping(value="/userssignup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    boolean addUser(@RequestBody UserInfo user) {

        if(!loginUser(user)) {
            System.out.println("its false");
            userRepository.save(user);
            System.out.println(user);
            return true;
        }
        System.out.println("its true");

        return false;
    }
}
