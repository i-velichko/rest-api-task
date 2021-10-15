package com.epam.esm.service;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:48
 */
public interface TagService {
    List<TagDto> findAll();

    TagDto findBy(long id);

    TagDto findBy(String name);

    TagDto create(TagDto tagDto);

    boolean delete(long id);
}
