package com.example.sw_midterm.mapper;

import com.example.sw_midterm.dto.UserDto;
import com.example.sw_midterm.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User userEntity);

    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toEntity(UserDto userDto);
}
