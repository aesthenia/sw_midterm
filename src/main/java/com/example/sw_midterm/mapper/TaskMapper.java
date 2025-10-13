package com.example.sw_midterm.mapper;

import com.example.sw_midterm.dto.TaskDto;
import com.example.sw_midterm.model.Task;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toDto(Task taskEntity);
    TaskDto toEntity(TaskDto taskDto);
}
