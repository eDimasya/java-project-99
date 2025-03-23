package hexlet.code.app.dto.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusDTO {
    private Long id;
    private String name;
    private String slug;
}
