package com.example.sw_midterm.controller;

import com.example.sw_midterm.mapper.TaskMapper;
import com.example.sw_midterm.service.TaskService;
import com.example.sw_midterm.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<TaskDto> tasks = taskService.getAll();
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        TaskDto dto = taskMapper.toDto(taskService.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody TaskDto dto){
        taskService.addTask(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDto dto){
        taskService.updateTask(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        if ( taskService.deleteTask(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}