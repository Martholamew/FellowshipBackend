package fellowshipofthemovieclub.fellowship.services;

import fellowshipofthemovieclub.fellowship.dtos.UserDTO;
import fellowshipofthemovieclub.fellowship.jpaentities.Role;
import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import fellowshipofthemovieclub.fellowship.repositories.RoleRepository;
import fellowshipofthemovieclub.fellowship.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordService passwordService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.roleRepository = roleRepository;
    }

    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    public UserDTO loginUser(UserDTO userDTO) {
        UserInfo userInfo = userRepository.findByUserName(userDTO.getUserName());
        if(userInfo!=null){
            if (passwordService.checkPassword(userDTO.getPassword(), userInfo.getPassword())) {
                return new UserDTO(userInfo.getId(), userInfo.getUserName(), userInfo.getEmail(), "Welcome back "+userInfo.getUserName());//Success
            } else {
                return new UserDTO("Invalid credentials");
            }
        }
        return new UserDTO("User does not exist");

    }

    public UserDTO addUser(UserDTO userDto, String roleName) {
        try {
            if (userRepository.existsByUserName(userDto.getUserName())) {
                return new UserDTO("User already exists");
            }
            String hashedPassword = passwordService.hashPassword(userDto.getPassword());
            UserInfo hashedUser = new UserInfo(userDto, hashedPassword);
            Role role = roleRepository.findByName(roleName);
            hashedUser.setRoles(Collections.singleton(role));
            userRepository.save(hashedUser);
            return new UserDTO(hashedUser.getId(), hashedUser.getUserName(), hashedUser.getEmail(), "Thanks for siging up "+hashedUser.getUserName());
        } catch (DataIntegrityViolationException e) { //checks constraints on database for unique name
            return new UserDTO("User already exists");
        }
    }

    public void assignRoleToUser(String userName, String roleName) {
        UserInfo user = userRepository.findByUserName(userName);

        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);
        userRepository.save(user);
    }

}
