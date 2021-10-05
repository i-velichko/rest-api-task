package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:53
 */
public interface GiftCertificateService {
    List<GiftCertificate> findAll();
}
