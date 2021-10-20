package com.epam.esm.mapper;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ConvertEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

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
        if (nonNull(dto)) {
            return mapper.map(dto, GiftCertificate.class);
        } else {
            throw new ConvertEntityException("Certificate conversion error");
        }
    }

    public GiftCertificateDto toDto(GiftCertificate entity) {
        if (nonNull(entity)) {
            return mapper.map(entity, GiftCertificateDto.class);
        } else {
            throw new ConvertEntityException("Certificate conversion error");
        }
    }
}
