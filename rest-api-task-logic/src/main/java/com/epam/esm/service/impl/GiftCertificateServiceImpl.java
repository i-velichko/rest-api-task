package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.CustomErrorMessageCode;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exception.NoSuchParameterException;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.util.GiftCertificateMerger;
import com.epam.esm.validator.impl.GiftCertificateDataValidator;
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
import static com.epam.esm.mapper.ParamName.*;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:53
 */

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDaoImpl giftCertificateDao;
    private final TagDaoImpl tagDao;
    private final GiftCertificateMapper certificateMapper;
    private GiftCertificateDataValidator certificateDataValidator;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDaoImpl giftCertificateDao, TagDaoImpl tagDao, GiftCertificateMapper certificateMapper, GiftCertificateDataValidator certificateDataValidator) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
        this.certificateMapper = certificateMapper;
        this.certificateDataValidator = certificateDataValidator;
    }

    @Override
    public GiftCertificateDto findBy(long id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDao.findBy(id);
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
    public List<GiftCertificateDto> findAllBy(Map<String, String> params) {
        List<String> sortParams = List.of(TAG, SORT, SEARCH, ORDER);
        if (params.isEmpty()) {
            return findAll();
        } else if (params.keySet().stream().noneMatch(param1 -> sortParams.stream().anyMatch(param1::equals))) {
            throw new NoSuchParameterException(CustomErrorMessageCode.PARAMETER_NOT_FOUND);
        } else {
            sortParams.forEach(sortParam -> params.putIfAbsent(sortParam, EMPTY_LINE));
            return giftCertificateDao.findAllBy(params)
                    .stream()
                    .map(certificateMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    @Transactional
    public GiftCertificateDto create(GiftCertificateDto giftCertificateDto) {
        GiftCertificate newGiftCertificate;
        GiftCertificate giftCertificate = certificateMapper.toEntity(giftCertificateDto);
        boolean ifExist = giftCertificateDao.findBy(giftCertificate.getId()).isPresent();
        if (!ifExist) {
            newGiftCertificate = giftCertificateDao.create(giftCertificate);
            long certificateId = newGiftCertificate.getId();
            Set<Tag> tags = giftCertificate.getTags();
            if (!tags.isEmpty()) {
                tags.forEach(tag -> tagDao.createWithReference(tag, certificateId));
            }
            giftCertificateDao.findBy(certificateId);
            return findBy(certificateId);
        } else {
            throw new DuplicateEntityException(CERTIFICATE_ALREADY_EXIST);
        }
    }

    @Override
    @Transactional
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto) {
        GiftCertificate updatedCertificate = new GiftCertificate();
        if (giftCertificateDto != null) {
            GiftCertificate newGiftCertificate = certificateMapper.toEntity(giftCertificateDto);
            GiftCertificate previousCertificate = certificateMapper.toEntity(findBy(giftCertificateDto.getId()));
            updatedCertificate = certificateDataValidator.checkDataForUpdateCertificate(newGiftCertificate, previousCertificate);
            if (updatedCertificate != null) {
                GiftCertificateMerger.merge(updatedCertificate, previousCertificate);
                updatedCertificate = giftCertificateDao.update(previousCertificate);
                long id = updatedCertificate.getId();
                previousCertificate.getTags()
                        .stream().filter(tag -> tagDao.findBy(tag.getName()).isEmpty())
                        .forEach(tag -> tagDao.createWithReference(tag, id));
            }
        }
        return certificateMapper.toDto(updatedCertificate);
    }

    @Transactional
    @Override
    public void delete(long id) {
        giftCertificateDao.findBy(id).orElseThrow(NoSuchEntityException::new);
        giftCertificateDao.deleteFromTagCertificateAssociateTable(id);
        giftCertificateDao.delete(id);
    }
}
