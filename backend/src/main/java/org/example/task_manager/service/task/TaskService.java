package org.example.task_manager.service.task;

import org.example.task_manager.dto.task.TaskCreatingDto;
import org.example.task_manager.model.Task;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Task create(TaskCreatingDto task);
    List<Task> getAll(Pageable pageable);
    Task getById(Long id);
    Task update(Long id, TaskCreatingDto task);
    void delete(Long id);
}
