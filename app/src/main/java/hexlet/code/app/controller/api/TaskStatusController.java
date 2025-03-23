package hexlet.code.app.controller.api;

import hexlet.code.app.dto.task.TaskStatusCreateDTO;
import hexlet.code.app.dto.task.TaskStatusDTO;
import hexlet.code.app.dto.task.TaskStatusUpdateDTO;
import hexlet.code.app.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task_statuses")
public class TaskStatusController {
    @Autowired
    private TaskStatusService service;

    @GetMapping("/{id}")
    public TaskStatusDTO show(@PathVariable Long id) {
        return service.show(id);
    }

    @GetMapping("/{slug}")
    public TaskStatusDTO show(@PathVariable String slug) {
        return service.show(slug);
    }

    @GetMapping
    public ResponseEntity<List<TaskStatusDTO>> index() {
        List<TaskStatusDTO> tasksStatuses = service.index();
        HttpHeaders headers = new HttpHeaders();
        headers.addIfAbsent("X-Total-Count", String.valueOf(tasksStatuses.size()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(tasksStatuses);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStatusDTO create(@RequestBody TaskStatusCreateDTO data) {
        return service.create(data);
    }

    @PutMapping("/{id}")
    public TaskStatusDTO update(@RequestBody TaskStatusUpdateDTO data, @PathVariable Long id) {
        return service.update(data, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
