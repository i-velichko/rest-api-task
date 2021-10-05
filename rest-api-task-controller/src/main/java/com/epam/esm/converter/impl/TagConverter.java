package com.epam.esm.converter.impl;

import com.epam.esm.converter.BaseEntityConverter;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 16:38
 */

@Component
public class TagConverter implements BaseEntityConverter<Tag, TagDto> {

    @Override
    public List<TagDto> convertAllToDtoList(List<Tag> tags) {
        return tags
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TagDto convertToDto(Tag tag) {
        return new TagDto(tag.getId(), tag.getName()
        );
    }

    @Override
    public Tag convertToEntity(TagDto tagDto) {
        return new Tag(tagDto.getId(), tagDto.getName());
    }

    @Override
    public List<Tag> convertAllToEntityList(List<TagDto> tagDtoList) {
        return tagDtoList
                .stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
