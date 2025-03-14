package hexlet.code.app.component;

import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import hexlet.code.app.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userService;

    @Override
    public void run(ApplicationArguments args) {
        String email = "admin@admin.com";
        User userData = new User();
        userData.setEmail(email);
        userData.setFirstName("adminFN");
        userData.setLastName("adminLN");
        userData.setPasswordDigest("admin");
        userService.createUser(userData);
    }
}
