package com.example.sw_midterm.service;


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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getAllTest(){
        List<UserDto> userDtos = userService.getAll();

        Assertions.assertNotNull(userDtos);
        Assertions.assertNotEquals(0, userDtos.size());

        userDtos.forEach(userDto -> {
            Assertions.assertNotNull(userDto.getId());
            Assertions.assertNotNull(userDto.getUsername());
            Assertions.assertNotNull(userDto.getEmail());
        });
    }

    @Test
    void getByIdTest(){
        Random random = new Random();

        int randomIndex = random.nextInt(userService.getAll().size());
        Long someIndex = userService.getAll().get(randomIndex).getId();
        UserDto userDto = userService.getById(someIndex);

        Assertions.assertNotNull(userDto);

        Assertions.assertNotNull(userDto.getId());
        Assertions.assertNotNull(userDto.getUsername());
        Assertions.assertNotNull(userDto.getEmail());

        UserDto dto = userService.getById(-1L);
        Assertions.assertNull(dto);
    }

    @Test
    void addUserTest() {
        UserDto user = new UserDto();
        user.setUsername("test_user");
        user.setEmail("test_user@example.com");
        user.setPassword("test_password");

        UserDto createdUser = userService.addUser(user);

        Assertions.assertNotNull(createdUser);

        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals(user.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    void updateUserTest() {
        List<UserDto> users = userService.getAll();
        Random random = new Random();
        UserDto existing = users.get(random.nextInt(users.size()));

        UserDto update = new UserDto();
        update.setUsername("UpdatedName");
        update.setEmail("updated@example.com");

        userService.updateUser(existing.getId(), update);

        UserDto updated = userService.getById(existing.getId());

        Assertions.assertEquals(update.getUsername(), updated.getUsername());
        Assertions.assertEquals(update.getEmail(), updated.getEmail());
    }

    @Test
    void deleteUserTest() {
        List<UserDto> users = userService.getAll();
        Random random = new Random();
        Long randomId = users.get(random.nextInt(users.size())).getId();

        boolean deleted = userService.deleteUser(randomId);
        Assertions.assertTrue(deleted);

        UserDto afterDelete = userService.getById(randomId);
        Assertions.assertNull(afterDelete);
    }
}