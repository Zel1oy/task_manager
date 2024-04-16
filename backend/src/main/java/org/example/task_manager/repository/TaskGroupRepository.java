package org.example.task_manager.repository;

import org.example.task_manager.model.Task;
import org.example.task_manager.model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
    @Query("SELECT TaskGroup.tasks FROM TaskGroup tg WHERE tg.id = :taskGroupId")
    List<Task> getAllTasksByTaskGroupId(Long taskGroupId);
}
