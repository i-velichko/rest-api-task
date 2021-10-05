package com.epam.esm.dao.impl;

import com.epam.esm.dao.BaseDao;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 17:44
 */

@Component
public class TagDaoImpl implements BaseDao<Tag> {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_TAGS_SQL = "SELECT * FROM tag";
    private static final String FIND_TAG_BY_ID_SQL = FIND_ALL_TAGS_SQL + " WHERE id =?";
    private static final String CREATE_NEW_TAG_SQL = "INSERT INTO tag(name) VALUES (?)";


    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Tag create(Tag tag) {
        jdbcTemplate.update(CREATE_NEW_TAG_SQL, tag.getName());
        return null;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(FIND_ALL_TAGS_SQL, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag findById(long id) {
        Tag tag = jdbcTemplate.queryForObject(FIND_TAG_BY_ID_SQL, new BeanPropertyRowMapper<>(Tag.class), id);
        return tag;
    }

    @Override
    public Tag update(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
