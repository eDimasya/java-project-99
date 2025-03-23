package hexlet.code.app.service;

import hexlet.code.app.dto.user.UserCreateDTO;
import hexlet.code.app.dto.user.UserDTO;
import hexlet.code.app.dto.user.UserUpdateDTO;
import hexlet.code.app.mapper.UserMapper;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository repository;

    public UserDTO show(Long id) {
        return mapper.map(
                repository.findById(id)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User with id " + id + " not found")));
    }

    public UserDTO create(UserCreateDTO data) {
        User user = mapper.map(data);
        return mapper.map(
                repository.save(user));
    }

    public List<UserDTO> index() {
        return repository
                .findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    public UserDTO update(UserUpdateDTO data, Long id) {
        User user = repository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User with id " + id + " not found"));
        mapper.update(data, user);
        repository.save(user);
        return mapper.map(user);
    }

    public void delete(Long id) {
        repository.delete(
                repository.findById(id).orElseThrow(() ->
                        new UsernameNotFoundException("User with id " + id + " not found")));
    }
}
