package com.example.sw_midterm.service;

import com.example.sw_midterm.dto.TagDto;
import com.example.sw_midterm.dto.TaskDto;
import com.example.sw_midterm.dto.UserDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Test
    void getAllTest() {
        List<TaskDto> tasks = taskService.getAll();

        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());

        tasks.forEach(task -> {
            Assertions.assertNotNull(task.getId());
            Assertions.assertNotNull(task.getTitle());
        });
    }

    @Test
    void getByIdTest() {
        List<TaskDto> allTasks = taskService.getAll();
        Random random = new Random();
        TaskDto existing = allTasks.get(random.nextInt(allTasks.size()));

        TaskDto found = taskService.getById(existing.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(existing.getTitle(), found.getTitle());

        TaskDto notFound = taskService.getById(-999L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTaskTest() {
        UserDto user = userService.getAll().get(0);
        TagDto tag = tagService.findAll().get(0);

        TaskDto newTask = new TaskDto();
        newTask.setTitle("Test");
        newTask.setDescription("Testing service");
        newTask.setDone(false);
        newTask.setUserId(user.getId());
        newTask.setTagIds(List.of(tag.getId()));

        TaskDto createdTask = taskService.addTask(newTask);

        Assertions.assertNotNull(createdTask);
        Assertions.assertNotNull(createdTask.getId());
        Assertions.assertEquals(newTask.getTitle(), createdTask.getTitle());
        Assertions.assertEquals(user.getId(), createdTask.getUserId());
        Assertions.assertTrue(createdTask.getTagIds().contains(tag.getId()));
    }

    @Test
    void updateTaskTest() {
        List<TaskDto> allTasks = taskService.getAll();
        Random random = new Random();
        TaskDto taskToUpdate = allTasks.get(random.nextInt(allTasks.size()));

        TaskDto updateInfo = new TaskDto();
        updateInfo.setTitle("Updated Title");
        updateInfo.setDone(!taskToUpdate.isDone());

        taskService.updateTask(taskToUpdate.getId(), updateInfo);

        TaskDto updated = taskService.getById(taskToUpdate.getId());

        Assertions.assertEquals("Updated Title", updated.getTitle());
        Assertions.assertNotEquals(taskToUpdate.isDone(), updated.isDone());
        Assertions.assertNotNull(updated.getUserId());
    }

    @Test
    void deleteTaskTest() {
        List<TaskDto> allTasks = taskService.getAll();
        Random random = new Random();
        Long idToDelete = allTasks.get(random.nextInt(allTasks.size())).getId();

        boolean deleted = taskService.deleteTask(idToDelete);
        Assertions.assertTrue(deleted);

        TaskDto afterDelete = taskService.getById(idToDelete);
        Assertions.assertNull(afterDelete);
    }
}