package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:48
 */
public interface TagService {
    List<Tag> findAll();
    Tag findById(long id);
    Tag findByName(String name);
    boolean delete(long id);
}
