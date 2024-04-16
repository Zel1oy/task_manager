package org.example.task_manager.service.task;

import org.example.task_manager.dto.task_group.TaskGroupCreatingDto;
import org.example.task_manager.dto.task_group.TaskGroupResponseDto;
import org.example.task_manager.model.Task;
import org.example.task_manager.model.TaskGroup;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskGroupService {
    TaskGroupResponseDto create(TaskGroupCreatingDto taskGroup);
    List<Task> getAllTasksOfCurrentTaskGroup(Long id);
    List<TaskGroup> getAll(Pageable pageable);
    void delete(Long id);
}
