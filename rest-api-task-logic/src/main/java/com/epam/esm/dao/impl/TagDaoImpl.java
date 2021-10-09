package com.epam.esm.dao.impl;

import com.epam.esm.dao.BaseDao;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 17:44
 */

@Component
public class TagDaoImpl implements BaseDao<Tag> {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_TAGS_SQL = "SELECT id, name FROM tag";
    private static final String FIND_TAG_BY_ID_SQL = FIND_ALL_TAGS_SQL + " WHERE id =?";
    private static final String FIND_TAG_BY_NAME_SQL = FIND_ALL_TAGS_SQL + " WHERE name =?";
    private static final String CREATE_NEW_TAG_SQL = "INSERT INTO tag (name) VALUES (?)";
    private static final String CREATE_NEW_TAG_REFERENCE_SQL =
            "INSERT INTO certificates_tags (certificate_id, tag_id) VALUES (?, ?);";
    private static final String DELETE_TAG_BY_ID_SQL = "DELETE FROM tag WHERE id =?";
    private static final String IF_TAG_PRESENT_SQL = "SELECT 1 FROM tag WHERE name LIKE ?";

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag create(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(CREATE_NEW_TAG_SQL, new String[]{"id"});
            statement.setString(1, tag.getName());
            return statement;
        }, keyHolder);
        long id = keyHolder.getKey().longValue();
        tag.setId(id);
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(FIND_ALL_TAGS_SQL, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Optional<Tag> findById(long id) {
        return jdbcTemplate.query(FIND_TAG_BY_ID_SQL, new BeanPropertyRowMapper<>(Tag.class), id).stream().findAny();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(FIND_TAG_BY_NAME_SQL, new BeanPropertyRowMapper<>(Tag.class), name).stream().findAny();
    }

    @Override
    public void update(Tag tag) {
        throw new UnsupportedOperationException("Operation is not supported");
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE_TAG_BY_ID_SQL, id) > 0;
    }

    public void createWithReference(Tag tag, long certificateId) {
        Tag createdTag = create(tag);
        jdbcTemplate.update(CREATE_NEW_TAG_REFERENCE_SQL, certificateId, createdTag.getId());
    }

    public boolean ifPresent(String name) {

        return jdbcTemplate.queryForObject(IF_TAG_PRESENT_SQL, new BeanPropertyRowMapper<>(Boolean.class), name);
    }
}
