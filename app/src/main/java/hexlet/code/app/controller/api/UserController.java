package hexlet.code.app.controller.api;

import hexlet.code.app.dto.user.UserCreateDTO;
import hexlet.code.app.dto.user.UserDTO;
import hexlet.code.app.dto.user.UserUpdateDTO;
import hexlet.code.app.service.UserService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO show(@PathVariable Long id) {
        return userService.show(id);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> index() {
        List<UserDTO> users = userService.index();
        HttpHeaders headers = new HttpHeaders();
        headers.addIfAbsent("X-Total-Count", String.valueOf(users.size()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(users);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserCreateDTO userData) {
        return userService.create(userData);
    }

    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserUpdateDTO userData, @PathVariable Long id) {
        return userService.update(userData, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
