package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.util.GiftCertificateMerger;
import com.epam.esm.validator.impl.GiftCertificateDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:53
 */

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDaoImpl giftCertificateDao;
    private final TagDaoImpl tagDao;
    private final GiftCertificateDataValidator giftCertificateDataValidator;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDaoImpl giftCertificateDao, TagDaoImpl tagDao,
                                      GiftCertificateDataValidator giftCertificateDataValidator) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
        this.giftCertificateDataValidator = giftCertificateDataValidator;
    }

    @Override
    public GiftCertificate findById(long id) {
        Optional<GiftCertificate> byId = giftCertificateDao.findById(id);
        return byId.orElseThrow(() -> new NoSuchEntityException("certificate not found"));
    }

    @Override
    public List<GiftCertificate> findAll() {
        return giftCertificateDao.findAll();
    }

    @Override
    @Transactional
    public GiftCertificate create(GiftCertificate giftCertificate) {
        GiftCertificate toCreate;
        boolean ifExist = giftCertificateDao.findByName(giftCertificate.getName()).isPresent();
        if (!ifExist && giftCertificateDataValidator.checkDataForCreateCertificate(giftCertificate)) {
            toCreate = giftCertificateDao.create(giftCertificate);
            long certificateId = toCreate.getId();
            Set<Tag> tags = toCreate.getTags();
            if (tags.size() > 0) {
                tags.forEach(tag -> tagDao.createWithReference(tag, certificateId));
            }
        } else {
            throw new DuplicateEntityException("can not create certificate");
        }

        return toCreate;
    }

    @Override
    @Transactional
    public void update(GiftCertificate toUpdate) {
        if (toUpdate != null) {
            GiftCertificate previous = findById(toUpdate.getId());
            GiftCertificate checkedToUpdate = giftCertificateDataValidator.checkDataForUpdateCertificate(toUpdate, previous);
            if (checkedToUpdate != null) {
                GiftCertificateMerger.merge(checkedToUpdate, previous);
                giftCertificateDao.update(previous);
                previous.getTags()
                        .stream().filter(tag -> tagDao.findByName(tag.getName()).isEmpty())
                        .forEach(tag -> tagDao.createWithReference(tag, previous.getId()));
            }
        }
    }
}
