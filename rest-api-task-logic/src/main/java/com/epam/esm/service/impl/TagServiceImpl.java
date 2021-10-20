package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.exception.CustomErrorMessageCode.TAG_ALREADY_EXIST;
import static com.epam.esm.exception.CustomErrorMessageCode.TAG_NOT_FOUND;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:50
 */

@Service
public class TagServiceImpl implements TagService {
    private final TagDaoImpl tagDao;
    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagDaoImpl tagDao, TagMapper tagMapper) {
        this.tagDao = tagDao;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagDto> findAll() {
        return tagDao.findAll()
                .stream()
                .map(tagMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findBy(long id) {
        return tagDao.findBy(id)
                .map(tagMapper::toDto)
                .orElseThrow(() -> new NoSuchEntityException(TAG_NOT_FOUND));
    }

    @Override
    public TagDto findBy(String name) {
        return tagDao.findBy(name)
                .map(tagMapper::toDto)
                .orElseThrow(() -> new NoSuchEntityException(TAG_NOT_FOUND));
    }

    @Override
    public TagDto create(TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        boolean ifExist = tagDao.findBy(tag.getName()).isPresent();
        if (!ifExist) {
            return tagMapper.toDto(tagDao.create(tag));
        } else {
            throw new DuplicateEntityException(TAG_ALREADY_EXIST);
        }
    }

    @Override
    public void delete(long id) {
        tagDao.delete(id);
    }

}
