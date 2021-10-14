package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ExtractCertificateDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivan Velichko
 * @date 07.10.2021 11:24
 */

@Component
public class GiftCertificateExtractor implements ResultSetExtractor<List<GiftCertificate>> {
    private final GiftCertificateRowMapper giftCertificateRowMapper;

    @Autowired
    public GiftCertificateExtractor(GiftCertificateRowMapper giftCertificateRowMapper) {
        this.giftCertificateRowMapper = giftCertificateRowMapper;
    }

    @Override
    public List<GiftCertificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, GiftCertificate> certificateMap = new LinkedHashMap<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            GiftCertificate giftCertificate = certificateMap.computeIfAbsent(id, cId -> mapRow(rs));
            Tag tag = new Tag(rs.getInt("tag_id"), rs.getString("tag_name"));
            giftCertificate.getTags().add(tag);
        }
        return new ArrayList<>(certificateMap.values());
    }

    private GiftCertificate mapRow(ResultSet rs) {
        try {
            return giftCertificateRowMapper.mapRow(rs, 1);
        } catch (SQLException e) {
            throw new ExtractCertificateDataException();
        }
    }

}
