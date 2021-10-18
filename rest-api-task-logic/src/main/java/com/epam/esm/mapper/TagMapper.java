package com.epam.esm.mapper;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ConvertEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

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
        if (nonNull(tagDto)) {
            return mapper.map(tagDto, Tag.class);
        } else {
            throw new ConvertEntityException("Tag conversion error");
        }
    }

    public TagDto toDto(Tag tag) {
        if (nonNull(tag)) {
            return mapper.map(tag, TagDto.class);
        } else {
            throw new ConvertEntityException("Tag conversion error");
        }
    }
}
