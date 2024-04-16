package org.example.task_manager.service.user;

import org.example.task_manager.dto.user.UserRegistrationRequestDto;
import org.example.task_manager.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User register(UserRegistrationRequestDto request);

    User getAuthenticatedUser();
}
