package hexlet.code.app.service;

import hexlet.code.app.dto.task.TaskStatusCreateDTO;
import hexlet.code.app.dto.task.TaskStatusDTO;
import hexlet.code.app.dto.task.TaskStatusUpdateDTO;
import hexlet.code.app.exception.ResourceNotFoundException;
import hexlet.code.app.mapper.TaskStatusMapper;
import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService {
    @Autowired
    private TaskStatusMapper mapper;

    @Autowired
    private TaskStatusRepository repository;

    public TaskStatusDTO show(Long id) {
        return mapper.map(
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Task status with id " + id + " not found")));
    }

    public TaskStatusDTO show(String slug) {
        return mapper.map(
                repository.findBySlug(slug)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Task status with slug " + slug + " not found")));
    }

    public TaskStatusDTO create(TaskStatusCreateDTO data) {
        TaskStatus taskStatus = mapper.map(data);
        return mapper.map(
                repository.save(taskStatus));
    }

    public List<TaskStatusDTO> index() {
        return repository
                .findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    public TaskStatusDTO update(TaskStatusUpdateDTO data, Long id) {
        TaskStatus taskStatus = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task status with id " + id + " not found"));
        mapper.update(data, taskStatus);
        repository.save(taskStatus);
        return mapper.map(taskStatus);
    }

    public void delete(Long id) {
        repository.delete(
                repository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Task status with id " + id + " not found")));
    }
}
