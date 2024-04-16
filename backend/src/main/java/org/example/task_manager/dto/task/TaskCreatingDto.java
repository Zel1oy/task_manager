package org.example.task_manager.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.task_manager.enums.Priority;
import org.example.task_manager.enums.Status;

import java.time.LocalDate;

@Data
public class TaskCreatingDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Priority priority;
    @NotNull
    private Status status;
    @NotNull
    private LocalDate dueDate;
}
