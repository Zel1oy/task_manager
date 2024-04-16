package org.example.task_manager.mapper;

import org.example.task_manager.config.MapperConfig;
import org.example.task_manager.dto.task_group.TaskGroupCreatingDto;
import org.example.task_manager.dto.task_group.TaskGroupResponseDto;
import org.example.task_manager.model.TaskGroup;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface TaskGroupMapper {
    TaskGroup toModel(TaskGroupCreatingDto requestDto);
    TaskGroupResponseDto toDto(TaskGroup taskGroup);
}
