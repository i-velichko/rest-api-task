package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.exception.DuplicateEntityException;

import java.util.List;
import java.util.Map;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:53
 */
public interface GiftCertificateService {
    GiftCertificateDto findById(long id);

    List<GiftCertificateDto> findAll();

    List<GiftCertificateDto> findAllByParam(Map<String, String> params);

    GiftCertificateDto create(GiftCertificateDto giftCertificateDto) throws DuplicateEntityException;

    GiftCertificateDto update(GiftCertificateDto giftCertificateDto);
}
