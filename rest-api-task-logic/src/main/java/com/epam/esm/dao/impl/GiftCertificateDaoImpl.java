package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 17:44
 */
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    @Override
    public GiftCertificate create(GiftCertificate entity) {
        return null;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return null;
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<GiftCertificate> update(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
