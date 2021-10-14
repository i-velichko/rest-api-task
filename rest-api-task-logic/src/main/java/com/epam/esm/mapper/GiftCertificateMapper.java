package com.epam.esm.mapper;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Ivan Velichko
 * @date 13.10.2021 23:58
 */

@Component
public class GiftCertificateMapper {
    private ModelMapper mapper;

    @Autowired
    public GiftCertificateMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public GiftCertificate toEntity(GiftCertificateDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, GiftCertificate.class);
    }

    public GiftCertificateDto toDto(GiftCertificate entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, GiftCertificateDto.class);
    }
}
