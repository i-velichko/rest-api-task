package com.epam.esm.dao;

import com.epam.esm.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 17:24
 */
public interface BaseDao<T extends BaseEntity> {
    T create(T entity);

    List<T> findAll();

    Optional<T> findById(long id);

    Optional<T> findByName(String name);

    T update(T t);

    boolean delete(long id);

}
