package com.example.sw_midterm.mapper;

import com.example.sw_midterm.dto.TagDto;
import com.example.sw_midterm.model.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagMapperTest {

    @Autowired
    private TagMapper tagMapper;

    @Test
    void toDtoTest() {
        Tag tag = new Tag(1L, "Important");
        TagDto dto = tagMapper.toDto(tag);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(tag.getId(), dto.getId());
        Assertions.assertEquals(tag.getName(), dto.getName());
    }

    @Test
    void toEntityTest() {
        TagDto dto = new TagDto(1L, "Not important");
        Tag tag = tagMapper.toEntity(dto);

        Assertions.assertNotNull(tag);
        Assertions.assertEquals(dto.getId(), tag.getId());
        Assertions.assertEquals(dto.getName(), tag.getName());
    }
}