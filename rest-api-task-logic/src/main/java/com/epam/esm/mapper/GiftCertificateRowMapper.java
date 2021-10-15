package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.esm.mapper.ParamName.*;

/**
 * @author Ivan Velichko
 * @date 06.10.2021 20:47
 */

@Component
public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {

    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(rs.getInt(ID));
        giftCertificate.setName(rs.getString(NAME));
        giftCertificate.setDescription(rs.getString(DESCRIPTION));
        giftCertificate.setPrice(rs.getBigDecimal(PRICE));
        giftCertificate.setCreateDate(rs.getTimestamp(CREATE_DATE).toLocalDateTime());
        giftCertificate.setLastUpdateDate(rs.getTimestamp(
                LAST_UPDATE_DATE).toLocalDateTime());
        giftCertificate.setDuration(rs.getInt(DURATION));
        return giftCertificate;
    }
}
