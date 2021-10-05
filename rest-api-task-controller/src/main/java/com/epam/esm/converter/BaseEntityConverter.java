package com.epam.esm.converter;

import com.epam.esm.dto.BaseDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.BaseEntity;
import com.epam.esm.entity.Tag;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 19:59
 */
public interface BaseEntityConverter<K extends BaseEntity, T extends BaseDto> {

    T convertToDto(K k);
    List<T> convertAllToDtoList(List<K> list );
    K convertToEntity(T t);
    List<K> convertAllToEntityList(List<T> list );
}
