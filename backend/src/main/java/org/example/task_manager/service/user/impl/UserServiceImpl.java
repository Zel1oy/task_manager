package org.example.task_manager.service.user.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.task_manager.dto.user.UserRegistrationRequestDto;
import org.example.task_manager.exception.RegistrationException;
import org.example.task_manager.mapper.UserMapper;
import org.example.task_manager.model.User;
import org.example.task_manager.repository.UserRepository;
import org.example.task_manager.service.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User register(UserRegistrationRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("User with email "
                    + request.getEmail() + " already exists");
        }
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
