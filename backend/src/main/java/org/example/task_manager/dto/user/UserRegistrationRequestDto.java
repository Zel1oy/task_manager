package org.example.task_manager.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 8, max = 255)
    private String password;
    @NotNull
    @Size(min = 8, max = 255)
    private String confirmPassword;
}
