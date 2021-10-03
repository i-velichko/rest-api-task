package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
