package com.example.sw_midterm.service;

import com.example.sw_midterm.mapper.TaskMapper;
import com.example.sw_midterm.model.Task;
import com.example.sw_midterm.repository.TaskRepository;
import com.example.sw_midterm.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;

    public void addTask(TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        taskRepository.save(task);
    }

    public List<TaskDto> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void updateTask(Long id, TaskDto updated){
        Task existing = getById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDone(updated.isDone());
        //existing.setUser(userService.getById(updated.getUserId()));
        taskRepository.save(existing);
    }

    public boolean deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            return false;
        }

        taskRepository.deleteById(id);
        return true;
    }
}