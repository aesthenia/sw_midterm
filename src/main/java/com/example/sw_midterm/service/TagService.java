package com.example.sw_midterm.service;

import com.example.sw_midterm.dto.TagDto;
import com.example.sw_midterm.mapper.TagMapper;
import com.example.sw_midterm.repository.TagRepository;
import com.example.sw_midterm.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public void addTag(TagDto dto){
        Tag tag = tagMapper.toEntity(dto);
        tagRepository.save(tag);
    }

    public List<TagDto> findAll(){
        return tagRepository.findAll()
                .stream()
                .map(tagMapper::toDto)
                .toList();
    }

    public TagDto findById(Long id){
        Tag tag = tagRepository.findById(id).orElse(null);
        return tagMapper.toDto(tag);
    }

    public void updateTag(Long id, TagDto dto){
        Tag updatedTag = tagMapper.toEntity(findById(id));
        updatedTag.setName(dto.getName());
        tagRepository.save(updatedTag);
    }

    public boolean deleteTag(Long id){
        Tag tag = tagMapper.toEntity(findById(id));
        if (tag == null) {
            return false;
        } else {
            tagRepository.delete(tag);
            return true;
        }
    }
}