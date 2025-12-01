package com.example.sw_midterm.controller;

import com.example.sw_midterm.dto.TagDto;
import com.example.sw_midterm.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
   private final TagService tagService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<TagDto> dtoList = tagService.findAll();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        TagDto dto = tagService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTag(@RequestBody TagDto dto){
        tagService.addTag(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Long id, @RequestBody TagDto dto){
        tagService.updateTag(id, dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id){
        if (tagService.deleteTag(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
