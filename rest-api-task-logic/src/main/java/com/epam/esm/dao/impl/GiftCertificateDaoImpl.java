package com.epam.esm.dao.impl;

import com.epam.esm.dao.BaseDao;
import com.epam.esm.dao.query.QueryBuilder;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.GiftCertificateExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.epam.esm.mapper.ParamName.QUERY;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 17:44
 */

@Repository
public class GiftCertificateDaoImpl implements BaseDao<GiftCertificate> {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final GiftCertificateExtractor giftCertificateExtractor;
    private final QueryBuilder queryBuilder;

    private static final String FIND_ALL_CERTIFICATES_WITH_TAGS_SQL = "SELECT c.id, c.name, " +
            "c.description, c.price, c.create_date, c.last_update_date, c.duration, " +
            "t.id as tag_id, t.name as tag_name " +
            "FROM gift_certificate as c " +
            "LEFT JOIN certificates_tags ct ON c.id = ct.certificate_id " +
            "LEFT JOIN tag t on t.id = ct.tag_id ";
    private static final String FIND_CERTIFICATE_BY_ID_SQL = FIND_ALL_CERTIFICATES_WITH_TAGS_SQL + " WHERE c.id =?";
    private static final String FIND_CERTIFICATE_BY_NAME_SQL = FIND_ALL_CERTIFICATES_WITH_TAGS_SQL + " WHERE c.name =?";
    private static final String UPDATE_GIFT_CERTIFICATE_SQL = "UPDATE gift_certificate SET name = ?, description = ?, " +
            "price = ?, last_update_date=NOW(), duration = ? WHERE id = ?";
    private static final String CREATE_NEW_CERTIFICATE_SQL = "INSERT INTO gift_certificate " +
            "(name, description, price, duration) VALUES (:name, :description, :price, :duration)";
    private static final String DELETE_BY_ID = "DELETE FROM gift_certificate WHERE id=?";
    private static final String DELETE_BY_ID_FROM_COMMON_TABLE = "DELETE FROM certificates_tags WHERE certificate_id=?";

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, GiftCertificateExtractor giftCertificateExtractor, QueryBuilder queryBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.giftCertificateExtractor = giftCertificateExtractor;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(CREATE_NEW_CERTIFICATE_SQL,
                new BeanPropertySqlParameterSource(giftCertificate), keyHolder, new String[]{"id"});
        giftCertificate.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(FIND_ALL_CERTIFICATES_WITH_TAGS_SQL, giftCertificateExtractor);
    }

    @Override
    public Optional<GiftCertificate> findBy(long id) {
        return jdbcTemplate.query(FIND_CERTIFICATE_BY_ID_SQL, giftCertificateExtractor, id).stream().findAny();
    }

    @Override
    public Optional<GiftCertificate> findBy(String name) {
        return jdbcTemplate.query(FIND_CERTIFICATE_BY_NAME_SQL, giftCertificateExtractor, name).stream().findAny();
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        namedParameterJdbcTemplate.update(CREATE_NEW_CERTIFICATE_SQL, new BeanPropertySqlParameterSource(giftCertificate));
        return giftCertificate;
    }

    public List<GiftCertificate> findAllBy(Map<String, String> params) {
        params.put(QUERY, FIND_ALL_CERTIFICATES_WITH_TAGS_SQL);
        String sqlQuery = queryBuilder.buildQueryForSearch(params);
        return jdbcTemplate.query(sqlQuery, giftCertificateExtractor);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    public void deleteFromTagCertificateAssociateTable(long id) {
        jdbcTemplate.update(DELETE_BY_ID_FROM_COMMON_TABLE, id);
    }
}
