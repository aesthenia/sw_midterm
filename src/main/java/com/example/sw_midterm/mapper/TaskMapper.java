package com.example.sw_midterm.mapper;

import com.example.sw_midterm.dto.TaskDto;
import com.example.sw_midterm.model.Task;
import com.example.sw_midterm.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "tags", target = "tagIds", qualifiedByName = "mapTagsToIds")
    TaskDto toDto(Task taskEntity);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "tags", ignore = true)
    Task toEntity(TaskDto taskDto);

    @Named("mapTagsToIds")
    static List<Long> mapTagsToIds(List<Tag> tags) {
        if (tags == null) return null;
        return tags.stream().map(Tag::getId).toList();
    }
}