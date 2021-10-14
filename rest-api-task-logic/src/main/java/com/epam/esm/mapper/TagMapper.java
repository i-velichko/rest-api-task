package com.epam.esm.mapper;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Ivan Velichko
 * @date 13.10.2021 19:49
 */

@Component
public class TagMapper {
    private ModelMapper mapper;


    @Autowired
    public TagMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Tag toEntity(TagDto tagDto) {
        return Objects.isNull(tagDto) ? null : mapper.map(tagDto, Tag.class);
    }

    public TagDto toDto(Tag tag) {
        return Objects.isNull(tag) ? null : mapper.map(tag, TagDto.class);
    }
}
