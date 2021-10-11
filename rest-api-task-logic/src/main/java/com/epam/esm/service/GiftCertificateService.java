package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.DuplicateEntityException;

import java.util.List;
import java.util.Map;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:53
 */
public interface GiftCertificateService {
    GiftCertificate findById(long id);

    List<GiftCertificate> findAll();

    List<GiftCertificate> findAllByParam(Map<String, String> params);

    GiftCertificate create(GiftCertificate giftCertificate) throws DuplicateEntityException;

    void update(GiftCertificate giftCertificate);
}
