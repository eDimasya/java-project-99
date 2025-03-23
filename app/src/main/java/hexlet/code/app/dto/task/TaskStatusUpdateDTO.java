package hexlet.code.app.dto.task;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;
}
