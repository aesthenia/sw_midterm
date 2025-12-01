package com.example.sw_midterm.service;

import com.example.sw_midterm.dto.TagDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    void findAllTest() {
        List<TagDto> tags = tagService.findAll();

        Assertions.assertNotNull(tags);
        Assertions.assertFalse(tags.isEmpty());

        tags.forEach(tag -> {
            Assertions.assertNotNull(tag.getId());
            Assertions.assertNotNull(tag.getName());
        });
    }

    @Test
    void findByIdTest() {
        Random random = new Random();
        List<TagDto> allTags = tagService.findAll();

        TagDto existingTag = allTags.get(random.nextInt(allTags.size()));

        TagDto foundTag = tagService.findById(existingTag.getId());

        Assertions.assertNotNull(foundTag);
        Assertions.assertEquals(existingTag.getId(), foundTag.getId());
        Assertions.assertEquals(existingTag.getName(), foundTag.getName());

        TagDto notFound = tagService.findById(-999L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTagTest() {
        TagDto newTag = new TagDto();
        newTag.setName("New_Test_Tag");

        tagService.addTag(newTag);

        List<TagDto> allTags = tagService.findAll();
        boolean exists = allTags.stream()
                .anyMatch(t -> t.getName().equals("New_Test_Tag"));

        Assertions.assertTrue(exists);
    }

    @Test
    void updateTagTest() {
        List<TagDto> tags = tagService.findAll();
        Random random = new Random();
        TagDto tagToUpdate = tags.get(random.nextInt(tags.size()));

        TagDto updateInfo = new TagDto();
        updateInfo.setName("Updated_Tag_Name");

        tagService.updateTag(tagToUpdate.getId(), updateInfo);

        TagDto updatedTag = tagService.findById(tagToUpdate.getId());
        Assertions.assertEquals("Updated_Tag_Name", updatedTag.getName());
    }

    @Test
    void deleteTagTest() {
        List<TagDto> tags = tagService.findAll();
        Random random = new Random();
        Long idToDelete = tags.get(random.nextInt(tags.size())).getId();

        boolean isDeleted = tagService.deleteTag(idToDelete);
        Assertions.assertTrue(isDeleted);

        TagDto afterDelete = tagService.findById(idToDelete);
        Assertions.assertNull(afterDelete);
    }
}