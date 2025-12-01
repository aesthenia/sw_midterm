package com.example.sw_midterm.mapper;

import com.example.sw_midterm.dto.TagDto;
import com.example.sw_midterm.model.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toDto(Tag tag);
    Tag toEntity(TagDto dto);
}
