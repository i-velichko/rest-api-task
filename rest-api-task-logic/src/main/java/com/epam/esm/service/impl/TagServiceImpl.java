package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.impl.TagDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:50
 */

@Service
public class TagServiceImpl implements TagService {
    private final TagDaoImpl tagDao;
    private final TagDataValidator tagDataValidator;

    @Autowired
    public TagServiceImpl(TagDaoImpl tagDao, TagDataValidator tagDataValidator) {
        this.tagDao = tagDao;
        this.tagDataValidator = tagDataValidator;
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public Tag findById(long id) {
        Optional<Tag> byId = tagDao.findById(id);
        return byId.orElseThrow(() -> new NoSuchEntityException("tag not found"));
    }

    @Override
    public Tag findByName(String name) {
        Optional<Tag> byName = tagDao.findByName(name);
        return byName.orElseThrow(() -> new NoSuchEntityException("tag not found"));
    }

    @Override
    public Tag create(Tag tag) {
        Tag toCreate;
        boolean ifExist = tagDao.findByName(tag.getName()).isPresent();
        if (!ifExist && tagDataValidator.isValid(tag)) {
            toCreate = tagDao.create(tag);
        } else {
            throw new DuplicateEntityException("can not create tag");
        }
        return toCreate;
    }

    @Override
    public boolean delete(long id) {
        return tagDao.delete(id);
    }
}
