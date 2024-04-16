package org.example.task_manager.mapper;

import org.example.task_manager.config.MapperConfig;
import org.example.task_manager.dto.task.TaskCreatingDto;
import org.example.task_manager.model.Task;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface TaskMapper {
    Task toModel(TaskCreatingDto taskCreatingDto);
}
