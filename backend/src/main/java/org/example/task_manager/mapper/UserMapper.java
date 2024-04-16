package org.example.task_manager.mapper;

import org.example.task_manager.config.MapperConfig;
import org.example.task_manager.dto.user.UserRegistrationRequestDto;
import org.example.task_manager.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto requestDto);
}
