package com.epam.esm.converter.impl;

import com.epam.esm.converter.TagMapper;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 16:38
 */

@Component
public class TagMapperImpl implements TagMapper {

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
    public Tag convertToTag(TagDto tagDto) {
        return new Tag(tagDto.getId(), tagDto.getName());
    }

    @Override
    public List<Tag> convertAllToTagList(List<TagDto> tagDtoList) {
        return tagDtoList
                .stream().map(this::convertToTag).collect(Collectors.toList());
    }
}
