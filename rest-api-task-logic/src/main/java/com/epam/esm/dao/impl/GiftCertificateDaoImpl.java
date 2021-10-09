package com.epam.esm.dao.impl;

import com.epam.esm.dao.BaseDao;
import com.epam.esm.dao.mapper.GiftCertificateExtractor;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.*;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 17:44
 */

@Component
public class GiftCertificateDaoImpl implements BaseDao<GiftCertificate> {
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateMapper giftCertificateMapper;
    private GiftCertificateExtractor giftCertificateExtractor;

    private static final String FIND_ALL_CERTIFICATES_SQL =
            "SELECT id, name, description, price, create_date, last_update_date, duration FROM gift_certificate";
    private static final String FIND_ALL_CERTIFICATES_WITH_TAGS_SQL = "SELECT c.id, c.name, " +
            "c.description, c.price, c.create_date, c.last_update_date, c.duration, " +
            "t.id as tag_id, t.name as tag_name " +
            "FROM gift_certificate as c " +
            "LEFT JOIN certificates_tags ct ON c.id = ct.certificate_id " +
            "LEFT JOIN tag t on t.id = ct.tag_id";
    private static final String FIND_CERTIFICATE_BY_ID_SQL = FIND_ALL_CERTIFICATES_WITH_TAGS_SQL + " WHERE c.id =?";
    private static final String UPDATE_GIFT_CERTIFICATE_SQL = "UPDATE gift_certificate SET name = ?, description = ?, " +
            "price = ?, last_update_date=NOW(), duration = ? WHERE id = ?";

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate, GiftCertificateMapper giftCertificateMapper,
                                  GiftCertificateExtractor giftCertificateExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.giftCertificateMapper = giftCertificateMapper;
        this.giftCertificateExtractor = giftCertificateExtractor;
    }

    @Override
    public GiftCertificate create(GiftCertificate entity) {
        return null;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(FIND_ALL_CERTIFICATES_WITH_TAGS_SQL, giftCertificateExtractor);
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        return jdbcTemplate.query(FIND_CERTIFICATE_BY_ID_SQL, giftCertificateExtractor, id).stream().findAny();
    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        return null;
    }

    public void update(GiftCertificate giftCertificate) {
        jdbcTemplate.update(con -> {
                    PreparedStatement statement = con.prepareStatement(UPDATE_GIFT_CERTIFICATE_SQL,
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    statement.setString(1, giftCertificate.getName());
                    statement.setString(2, giftCertificate.getDescription());
                    statement.setBigDecimal(3, giftCertificate.getPrice());
                    statement.setInt(4, giftCertificate.getDuration());
                    statement.setLong(5, giftCertificate.getId());
                    return statement;
                }
        );

    }

    @Override
    public boolean delete(long id) {
        return false;
    }

}
