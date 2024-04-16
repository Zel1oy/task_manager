package org.example.task_manager.dto.exception;

import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@Data
public class ArgumentNotValidResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String[] errors;
}
