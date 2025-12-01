package com.example.sw_midterm.mapper;


import com.example.sw_midterm.dto.UserDto;
import com.example.sw_midterm.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void convertEntityToDtoTest() {
        User user = new User(1L, "ilya", "@mail.ru", "aksdamsd", null, null);
        UserDto dto = userMapper.toDto(user);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getUsername());
        Assertions.assertNotNull(dto.getEmail());
        Assertions.assertNotNull(dto.getPassword());

        Assertions.assertEquals(user.getId(), dto.getId());
        Assertions.assertEquals(user.getUsername(), dto.getUsername());
        Assertions.assertEquals(user.getEmail(), dto.getEmail());
        Assertions.assertEquals(user.getPassword(), dto.getPassword());
    }

    @Test
    void convertDtoToEntityTest(){
        UserDto dto = new UserDto(1L, "ilya", "@mail.ru", "asdasdasd");
        User user = userMapper.toEntity(dto);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getUsername());
        Assertions.assertNotNull(user.getEmail());
        Assertions.assertNotNull(user.getPassword());

        Assertions.assertEquals(dto.getId(), user.getId());
        Assertions.assertEquals(dto.getUsername(), user.getUsername());
        Assertions.assertEquals(dto.getEmail(), user.getEmail());
        Assertions.assertEquals(dto.getPassword(), user.getPassword());

    }
}