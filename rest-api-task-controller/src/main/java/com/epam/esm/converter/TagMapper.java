package com.epam.esm.converter;

import com.epam.esm.dto.BaseDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.BaseEntity;
import com.epam.esm.entity.Tag;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:41
 */
public interface TagMapper {

    TagDto convertToDto(Tag tag);
    List<TagDto> convertAllToDtoList(List<Tag> tags );
    Tag convertToTag(TagDto tagDto);
    List<Tag> convertAllToTagList(List<TagDto> tagDtoList );
}
