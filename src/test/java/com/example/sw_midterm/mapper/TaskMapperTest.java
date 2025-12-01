package com.example.sw_midterm.mapper;

import com.example.sw_midterm.dto.TaskDto;
import com.example.sw_midterm.model.Tag;
import com.example.sw_midterm.model.Task;
import com.example.sw_midterm.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void toDtoTest() {
        User user = new User();
        user.setId(10L);
        user.setUsername("nurken");

        Tag tag1 = new Tag(1L, "Work");
        Tag tag2 = new Tag(2L, "Study");

        Task task = new Task();
        task.setId(100L);
        task.setTitle("Test title");
        task.setDescription("Test description");
        task.setDone(true);
        task.setUser(user);
        task.setTags(List.of(tag1, tag2));

        TaskDto dto = taskMapper.toDto(task);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(task.getId(), dto.getId());
        Assertions.assertEquals(task.getTitle(), dto.getTitle());
        Assertions.assertEquals(task.getDescription(), dto.getDescription());
        Assertions.assertEquals(task.isDone(), dto.isDone());

        Assertions.assertEquals(user.getId(), dto.getUserId());

        Assertions.assertNotNull(dto.getTagIds());
        Assertions.assertEquals(2, dto.getTagIds().size());
        Assertions.assertTrue(dto.getTagIds().contains(1L));
        Assertions.assertTrue(dto.getTagIds().contains(2L));
    }

        @Test
        void toEntityTest() {
            TaskDto dto = new TaskDto();
            dto.setId(50L);
            dto.setTitle("DTO Title");
            dto.setUserId(5L);
            dto.setTagIds(List.of(1L, 2L));

            Task task = taskMapper.toEntity(dto);

            Assertions.assertNotNull(task);
            Assertions.assertEquals(dto.getId(), task.getId());
            Assertions.assertEquals(dto.getTitle(), task.getTitle());

            Assertions.assertNotNull(task.getUser());
            Assertions.assertEquals(dto.getUserId(), task.getUser().getId());

            Assertions.assertNull(task.getTags());

    }
}
