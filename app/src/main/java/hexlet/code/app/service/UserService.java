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
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public UserDTO show(Long id) {
        return userMapper.map(
                userRepository.findById(id)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User with id " + id + " not found")));
    }

    public UserDTO create(UserCreateDTO userData) {
        User user = userMapper.map(userData);
        return userMapper.map(
                userRepository.save(user));
    }

    public List<UserDTO> index() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::map)
                .toList();
    }

    public UserDTO update(UserUpdateDTO userData, Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User with id " + id + " not found"));
        userMapper.update(userData, user);
        return userMapper.map(user);
    }

    public void delete(Long id) {
        userRepository.delete(
                userRepository.findById(id).orElseThrow(() ->
                        new UsernameNotFoundException("User with id " + id + " not found")));
    }
}
