package hexlet.code.app.component;

import hexlet.code.app.util.TaskUtils;
import hexlet.code.app.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private TaskUtils taskUtils;

    @Override
    public void run(ApplicationArguments args) {
        userUtils.addUser("admin@admin.com", "adminFN", "adminLN", "admin");
        taskUtils.addStatus("Draft", "draft");
        taskUtils.addStatus("ToReview", "to_review");
        taskUtils.addStatus("ToBeFixed", "to_be_fixed");
        taskUtils.addStatus("ToPublish", "to_publish");
        taskUtils.addStatus("Published", "published");
    }

}
