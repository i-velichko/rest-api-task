package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.util.GiftCertificateMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.epam.esm.exception.CustomErrorMessageCode.CERTIFICATE_ALREADY_EXIST;
import static com.epam.esm.exception.CustomErrorMessageCode.CERTIFICATE_NOT_FOUND;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:53
 */

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDaoImpl giftCertificateDao;
    private final TagDaoImpl tagDao;
    private final GiftCertificateMapper certificateMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDaoImpl giftCertificateDao, TagDaoImpl tagDao, GiftCertificateMapper certificateMapper) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
        this.certificateMapper = certificateMapper;
    }

    @Override
    public GiftCertificateDto findById(long id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDao.findById(id);
        return optionalGiftCertificate
                .map(certificateMapper::toDto)
                .orElseThrow(() -> new NoSuchEntityException(CERTIFICATE_NOT_FOUND));
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        return giftCertificateDao.findAll()
                .stream()
                .map(certificateMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GiftCertificateDto> findAllByParam(Map<String, String> params) {
        return giftCertificateDao.findAllByParam(params)
                .stream()
                .map(certificateMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GiftCertificateDto create(GiftCertificateDto giftCertificateDto) {
        GiftCertificate newGiftCertificate;
        GiftCertificate giftCertificate = certificateMapper.toEntity(giftCertificateDto);
        boolean ifExist = giftCertificateDao.findById(giftCertificate.getId()).isPresent();
        if (!ifExist) {
            newGiftCertificate = giftCertificateDao.create(giftCertificate);
            long certificateId = giftCertificate.getId();
            Set<Tag> tags = giftCertificate.getTags();
            if (!tags.isEmpty()) {
                tags.forEach(tag -> tagDao.createWithReference(tag, certificateId));
            }
        } else {
            throw new DuplicateEntityException(CERTIFICATE_ALREADY_EXIST);
        }
        return certificateMapper.toDto(newGiftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto) {
        GiftCertificate updatedCertificate = new GiftCertificate();
        if (giftCertificateDto != null) {
            GiftCertificate newGiftCertificate = certificateMapper.toEntity(giftCertificateDto);
            GiftCertificate previousCertificate = certificateMapper.toEntity(findById(giftCertificateDto.getId()));
            GiftCertificateMerger.merge(newGiftCertificate, previousCertificate);
            updatedCertificate = giftCertificateDao.update(previousCertificate);
            previousCertificate.getTags()
                    .stream()
                    .filter(tag -> tagDao.findByName(tag.getName()).isEmpty())
                    .forEach(tag -> tagDao.createWithReference(tag, previousCertificate.getId()));
        }
        return certificateMapper.toDto(updatedCertificate);
    }
}
