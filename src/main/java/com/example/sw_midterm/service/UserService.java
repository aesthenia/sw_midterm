package com.example.sw_midterm.service;

import com.example.sw_midterm.mapper.UserMapper;
import com.example.sw_midterm.model.User;
import com.example.sw_midterm.repository.UserRepository;
import com.example.sw_midterm.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    public void addUser(UserDto dto) {
        User user = userMapper.toEntity(dto);
        //user.setUser(userService.getById(dto.getUserId()));
        userRepository.save(user);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(Long id, UserDto updated){
        User existing = getById(id);
        existing.setUsername(updated.getUsername());
        existing.setEmail(updated.getEmail());
        userRepository.save(existing);
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }
}