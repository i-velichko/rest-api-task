package com.epam.esm.controller;

import com.epam.esm.converter.TagMapper;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 16:41
 */

@RestController
@RequestMapping("/api")
public class TagController {
    private final TagMapper tagMapper;
    private final TagService tagService;

    @Autowired
    public TagController(TagMapper tagMapper, TagService tagService) {
        this.tagMapper = tagMapper;
        this.tagService = tagService;
    }

    @Autowired


    @GetMapping("/tags")
    public List<TagDto> showAll() {
        List<Tag> tags = tagService.findAll();
        return tagMapper.convertAllToDtoList(tags);
    }
}
