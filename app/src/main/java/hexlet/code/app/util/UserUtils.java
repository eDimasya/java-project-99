package hexlet.code.app.util;

import hexlet.code.app.dto.user.UserCreateDTO;
import hexlet.code.app.dto.user.UserDTO;
import hexlet.code.app.exception.UserAlreadyAddedException;
import hexlet.code.app.mapper.UserMapper;
import hexlet.code.app.model.User;
import hexlet.code.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import hexlet.code.app.repository.UserRepository;

@Component
@AllArgsConstructor
public class UserUtils {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        var email = authentication.getName();
        return userRepository.findByEmail(email).get();
    }

    public UserDTO addUser(String email, String firstName, String lastName, String password) {
        if (userRepository.findByEmail(email).isEmpty()) {
            UserCreateDTO user = new UserCreateDTO();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            return userService.create(user);
        } else {
            System.out.println("User with email: " + email + " already added");
            return userMapper.map(
                    userRepository.findByEmail(email).get());
        }
    }
}
