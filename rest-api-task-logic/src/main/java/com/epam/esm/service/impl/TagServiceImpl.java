package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.service.TagService;
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

    @Autowired
    public TagServiceImpl(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
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
    public boolean delete(long id) {
        //todo check is used count where tagid == id and if used throw my Exc

        return tagDao.delete(id);
    }
}
