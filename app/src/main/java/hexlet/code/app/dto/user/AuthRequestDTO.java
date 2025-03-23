package hexlet.code.app.dto.user;

import hexlet.code.app.config.SecurityConfig;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    @Email
    private String username;

    @Size(min = SecurityConfig.MIN_PASSWORD_LENGTH)
    private String password;
}
