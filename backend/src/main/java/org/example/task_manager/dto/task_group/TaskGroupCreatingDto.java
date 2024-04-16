package org.example.task_manager.dto.task_group;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskGroupCreatingDto {
    @NotNull
    private String name;
}
