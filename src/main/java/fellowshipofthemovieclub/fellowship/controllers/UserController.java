package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.dtos.UserDTO;
import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import fellowshipofthemovieclub.fellowship.services.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
//don't leave this as all
@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    public List<UserInfo> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/login")
    UserDTO loginUser(@RequestBody UserDTO userDTO) {
       return userService.loginUser(userDTO);
    }

    @PostMapping(value="/register")
    UserDTO addUser(@RequestBody UserDTO userDto) {
        String roleName = "USER";
       return userService.addUser(userDto, roleName);
    }
}
