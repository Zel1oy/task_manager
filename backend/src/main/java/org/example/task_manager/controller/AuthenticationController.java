package org.example.task_manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.task_manager.dto.user.UserLoginRequestDto;
import org.example.task_manager.dto.user.UserLoginResponseDto;
import org.example.task_manager.dto.user.UserRegistrationRequestDto;
import org.example.task_manager.model.User;
import org.example.task_manager.service.user.AuthenticationService;
import org.example.task_manager.service.user.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication API",
        description = "Controller for registering and authenticating users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Login user",
            description = "Check if we have such user in DB")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @Operation(summary = "Register user",
            description = "Register new user and giving him USER role")
    @PostMapping("/register")
    public User register(@RequestBody @Valid UserRegistrationRequestDto request) {
        return userService.register(request);
    }

    @GetMapping("/user")
    public User getUser() {
        return userService.getAuthenticatedUser();
    }
}
