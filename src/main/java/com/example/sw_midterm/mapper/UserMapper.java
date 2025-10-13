package com.example.sw_midterm.mapper;

import com.example.sw_midterm.dto.UserDto;
import com.example.sw_midterm.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDto(User user);
    User toEntity(UserDto userDto);
}
