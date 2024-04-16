package org.example.task_manager.service.task.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.task_manager.dto.task_group.TaskGroupCreatingDto;
import org.example.task_manager.dto.task_group.TaskGroupResponseDto;
import org.example.task_manager.mapper.TaskGroupMapper;
import org.example.task_manager.model.Task;
import org.example.task_manager.model.TaskGroup;
import org.example.task_manager.repository.TaskGroupRepository;
import org.example.task_manager.service.task.TaskGroupService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskGroupServiceImpl implements TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;
    private final TaskGroupMapper taskGroupMapper;

    @Override
    public TaskGroupResponseDto create(TaskGroupCreatingDto taskGroupRequest) {
        TaskGroup saved = taskGroupRepository.save(taskGroupMapper.toModel(taskGroupRequest));
        return taskGroupMapper.toDto(saved);
    }

    @Override
    public List<Task> getAllTasksOfCurrentTaskGroup(Long id) {
        if (!taskGroupRepository.existsById(id)) {
            throw new EntityNotFoundException("Task group with id " + id + " not found");
        }
        return taskGroupRepository.getAllTasksByTaskGroupId(id);
    }

    @Override
    public List<TaskGroup> getAll(Pageable pageable) {
        return taskGroupRepository.findAll(pageable).stream().toList();
    }

    @Override
    public void delete(Long id) {
        if (!taskGroupRepository.existsById(id)) {
            throw new IllegalArgumentException("Task group with id " + id + " not found");
        }
        taskGroupRepository.deleteById(id);
    }
}
