package fellowshipofthemovieclub.fellowship.services;

import fellowshipofthemovieclub.fellowship.dtos.RoleDTO;
import fellowshipofthemovieclub.fellowship.dtos.UserDTO;
import fellowshipofthemovieclub.fellowship.jpaentities.Role;
import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import fellowshipofthemovieclub.fellowship.repositories.RoleRepository;
import fellowshipofthemovieclub.fellowship.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final RoleRepository roleRepository;
    private final JwtTokenService jwtTokenService;

    public UserService(UserRepository userRepository, PasswordService passwordService, RoleRepository roleRepository, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.roleRepository = roleRepository;
        this.jwtTokenService = jwtTokenService;
    }

    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserDTO loginUser(UserDTO userDTO) {
        UserInfo userInfo = userRepository.findByUserName(userDTO.getUserName());
        if (userInfo != null) {
            if (passwordService.checkPassword(userDTO.getPassword(), userInfo.getPassword())) {
                String token = jwtTokenService.generateToken(userInfo);
                jwtTokenService.getRoleFromToken(token);

                // Convert UserInfo to UserDTO
                List<RoleDTO> roleDTOs = userInfo.getRoles().stream()
                        .map(role -> new RoleDTO(role.getId(), role.getName()))
                        .collect(Collectors.toList());
                return new UserDTO(userInfo.getId(), userInfo.getUserName(), userInfo.getEmail(),
                        "Welcome back " + userInfo.getUserName(), roleDTOs, token);  // Success
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

            // Hash the password
            String hashedPassword = passwordService.hashPassword(userDto.getPassword());

            UserInfo hashedUser = new UserInfo(userDto, hashedPassword);
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(roleName));
            hashedUser.setRoles(roles);
            userRepository.save(hashedUser);
            List<RoleDTO> roleDTOs = hashedUser.getRoles().stream()
                    .map(role -> new RoleDTO(role.getId(), role.getName()))
                    .collect(Collectors.toList());
            return new UserDTO(hashedUser.getId(), hashedUser.getUserName(), hashedUser.getEmail(),
                    "Thanks for signing up " + hashedUser.getUserName(),
                    roleDTOs, jwtTokenService.generateToken(hashedUser));
        } catch (DataIntegrityViolationException e) {
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
