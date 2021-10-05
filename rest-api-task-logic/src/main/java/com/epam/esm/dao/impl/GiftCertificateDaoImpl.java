package com.epam.esm.dao.impl;

import com.epam.esm.dao.BaseDao;
import com.epam.esm.entity.GiftCertificate;
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
public class GiftCertificateDaoImpl implements BaseDao<GiftCertificate> {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_CERTIFICATES_SQL = "SELECT * FROM gift_certificate";

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate create(GiftCertificate entity) {
        return null;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(FIND_ALL_CERTIFICATES_SQL, new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public GiftCertificate findById(long id) {
        return null;
    }

    @Override
    public GiftCertificate update(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
