package org.example.task_manager.service.task.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.example.task_manager.dto.task.TaskCreatingDto;
import org.example.task_manager.mapper.TaskMapper;
import org.example.task_manager.model.Task;
import org.example.task_manager.model.User;
import org.example.task_manager.repository.TaskRepository;
import org.example.task_manager.service.task.TaskService;
import org.example.task_manager.service.user.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;

    @Override
    public Task create(TaskCreatingDto task) {
        return taskRepository.save(taskMapper.toModel(task));
    }

    @Override
    public List<Task> getAll(Pageable pageable) {
        User user = userService.getAuthenticatedUser();
        return taskRepository.findAll(pageable).stream().toList();
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task not found"));
    }

    @Override
    public Task update(Long id, TaskCreatingDto task) {
        Task taskToUpdate = taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task not found"));
        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setPriority(task.getPriority());
        taskToUpdate.setStatus(task.getStatus());
        taskToUpdate.setDueDate(task.getDueDate());
        return taskRepository.save(taskToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
