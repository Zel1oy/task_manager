package org.example.task_manager.service.user;

import org.example.task_manager.dto.user.UserLoginRequestDto;
import org.example.task_manager.dto.user.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto request);
}
