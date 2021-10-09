package com.epam.esm.controller;

import com.epam.esm.converter.BaseEntityConverter;
import com.epam.esm.converter.impl.TagConverter;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 16:41
 */

@RestController
@RequestMapping("/v1/tags")
public class TagController {
    private final TagConverter tagConverter;
    private final TagService tagService;

    @Autowired
    public TagController( TagConverter tagConverter, TagService tagService) {
        this.tagConverter = tagConverter;
        this.tagService = tagService;
    }

    @GetMapping(value = "/{id}")
    public TagDto findTagById(@PathVariable("id") long id) {
        Tag tag = tagService.findById(id);
        return tagConverter.convertToDto(tag);
    }

    @GetMapping(params = "name")
    public TagDto findTagByName(@RequestParam(value = "name") String name) {
        Tag tag = tagService.findByName(name);
        return tagConverter.convertToDto(tag);
    }

    @GetMapping
    public List<TagDto> showAll() {
        List<Tag> tags = tagService.findAll();
        return tagConverter.convertAllToDtoList(tags);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag create(@RequestBody TagDto tagDto) {
        Tag tag = tagConverter.convertToEntity(tagDto);
        return tagService.create(tag);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable("id") long id) {
        return tagService.delete(id);
    }

}
