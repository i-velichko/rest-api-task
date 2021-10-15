package com.epam.esm.controller;

import com.epam.esm.dto.TagDto;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 16:41
 */

@RestController
@RequestMapping("/v1/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto findTagById(@PathVariable("id") long id) {
        return tagService.findBy(id);
    }

    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public TagDto findTagByName(@RequestParam(value = "name") String name) {
        return tagService.findBy(name);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagDto> findAll() {
        return tagService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto create(@RequestBody @Valid TagDto tagDto) {
        return tagService.create(tagDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@PathVariable("id") long id) {
        return tagService.delete(id);
    }

}
