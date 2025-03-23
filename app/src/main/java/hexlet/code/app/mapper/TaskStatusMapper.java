package hexlet.code.app.mapper;

import hexlet.code.app.dto.task.TaskStatusCreateDTO;
import hexlet.code.app.dto.task.TaskStatusDTO;
import hexlet.code.app.dto.task.TaskStatusUpdateDTO;
import hexlet.code.app.model.TaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskStatusMapper {
    public abstract TaskStatus map(TaskStatusDTO model);
    public abstract TaskStatus map(TaskStatusCreateDTO model);
    public abstract TaskStatus map(TaskStatusUpdateDTO model);
    public abstract TaskStatusDTO map(TaskStatus model);
    public abstract void update(TaskStatusUpdateDTO update, @MappingTarget TaskStatus destination);
}
