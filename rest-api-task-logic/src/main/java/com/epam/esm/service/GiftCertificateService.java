package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:53
 */
public interface GiftCertificateService {
    GiftCertificate findById(long id);
    List<GiftCertificate> findAll();
    void update(GiftCertificate giftCertificate);
}
