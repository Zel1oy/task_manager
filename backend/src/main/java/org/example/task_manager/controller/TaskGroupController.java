package org.example.task_manager.controller;

import lombok.RequiredArgsConstructor;
import org.example.task_manager.model.Task;
import org.example.task_manager.model.TaskGroup;
import org.example.task_manager.service.task.TaskGroupService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task_groups")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskGroupController {
    private final TaskGroupService taskGroupService;

    @GetMapping
    public List<TaskGroup> getTaskGroups(Pageable pageable) {
        return taskGroupService.getAll(pageable);
    }

    @GetMapping("/task_groups/{id}/tasks")
    public List<Task> getTasks(@PathVariable Long id) {
        return taskGroupService.getAllTasksOfCurrentTaskGroup(id);
    }
}
