package hexlet.code.app.component;

import hexlet.code.app.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private UserUtils userUtils;

    @Override
    public void run(ApplicationArguments args) {
        userUtils.addUser("admin@admin.com", "adminFN", "adminLN", "admin");
    }

}
