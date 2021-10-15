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

    Optional<T> findBy(long id);

    Optional<T> findBy(String name);

    T update(T t);

    void delete(long id);

}
