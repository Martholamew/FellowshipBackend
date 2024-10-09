package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import fellowshipofthemovieclub.fellowship.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // standard constructors

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<UserInfo> getUsers() {

        System.out.println("in get mapping");
        return (List<UserInfo>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody UserInfo user) {
        userRepository.save(user);
    }
}
