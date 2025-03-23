package hexlet.code.app.util;

import hexlet.code.app.dto.task.TaskStatusCreateDTO;
import hexlet.code.app.dto.task.TaskStatusDTO;
import hexlet.code.app.mapper.TaskStatusMapper;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskUtils {
    @Autowired
    private TaskStatusRepository repository;

    @Autowired
    private TaskStatusService service;

    @Autowired
    private TaskStatusMapper mapper;

    public TaskStatusDTO addStatus(String name, String slug) {
        if (repository.findBySlug(slug).isEmpty()) {
            TaskStatusCreateDTO taskStatus = new TaskStatusCreateDTO();
            taskStatus.setName(name);
            taskStatus.setSlug(slug);
            return service.create(taskStatus);
        } else {
            System.out.println("Status with slug: " + slug + " already added");
            return mapper.map(
                    repository.findBySlug(slug).get());
        }
    }
}
