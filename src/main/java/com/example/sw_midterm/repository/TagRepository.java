package com.example.sw_midterm.repository;

import com.example.sw_midterm.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Long id(Long id);
}