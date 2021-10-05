package com.epam.esm.converter.impl;

import com.epam.esm.converter.BaseEntityConverter;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:54
 */

@Component
public class GiftCertificateConverter implements BaseEntityConverter<GiftCertificate, GiftCertificateDto> {

    @Override
    public GiftCertificateDto convertToDto(GiftCertificate certificate) {
        return null;
    }

    @Override
    public List<GiftCertificateDto> convertAllToDtoList(List<GiftCertificate> list) {
        return null;
    }

    @Override
    public GiftCertificate convertToEntity(GiftCertificateDto giftCertificateDto) {
        return null;
    }

    @Override
    public List<GiftCertificate> convertAllToEntityList(List<GiftCertificateDto> list) {
        return null;
    }
}
