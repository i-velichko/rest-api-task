package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exception.NoSuchParameterException;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.validator.impl.GiftCertificateDataValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GiftCertificateServiceImplTest {
    private static final int EXPECTED_CERTIFICATES_SIZE = 2;
    private static final long ID_ONE = 1;
    private static final long ID_TWO = 2;
    private GiftCertificateServiceImpl toTest;
    private Map<String, String> filterParams;
    private Map<String, String> wrongFilterParams;
    private GiftCertificateDto certificate1;

    @Mock
    private GiftCertificateDaoImpl giftCertificateDao;
    @Mock
    private TagDaoImpl tagDao;
    @Mock
    private GiftCertificateMapper mapper;
    @Mock
    private GiftCertificateDataValidator validator;

    @BeforeEach
    void setUp() {
        openMocks(this);

        Set<TagDto> tagDtoSet = Set.of(new TagDto(3, "tag1"), new TagDto(4, "tag2"));
        Set<Tag> tags = Set.of(new Tag(3, "tag3"), new Tag(4, "tag4"));

        certificate1 = new GiftCertificateDto(1, "certificate1", "description1", valueOf(10), now(), now(), 10, tagDtoSet);
        GiftCertificate certificate3 = new GiftCertificate(1, "certificate1", "description1", valueOf(10), now(), now(), 10, tags);
        GiftCertificateDto certificate2 = new GiftCertificateDto(2, "certificate2", "description2", valueOf(10), now(), now(), 10, tagDtoSet);
        GiftCertificate certificate4 = new GiftCertificate(2, "certificate2", "description2", valueOf(10), now(), now(), 10, tags);
        List<GiftCertificate> certificates = List.of(certificate3, certificate4);

        filterParams = new HashMap<>();
        wrongFilterParams = new HashMap<>();
        filterParams.put("search", "certificate");
        wrongFilterParams.put("wrong", "wrong");

        when(giftCertificateDao.findAll()).thenReturn(certificates);
        when(giftCertificateDao.findAllBy(filterParams)).thenReturn(certificates);
        when(giftCertificateDao.findBy(1)).thenReturn(Optional.of(certificate3));
        when(mapper.toDto(eq(certificate3))).thenReturn(certificate1);
        when(mapper.toDto(eq(certificate4))).thenReturn(certificate2);

        toTest = new GiftCertificateServiceImpl(giftCertificateDao, tagDao, mapper, validator);
    }

    @Test
    void findByIdPositive() {

        GiftCertificateDto actualCertificate = toTest.findBy(ID_ONE);
        assertEquals(certificate1, actualCertificate);
    }

    @Test
    void findByIdExpectedExceptionWhenCertificateNotExist() {
        assertThrows(NoSuchEntityException.class, () -> {
            toTest.findBy(ID_TWO);
        });
    }

    @Test
    void findAll() {
        int actualSize = toTest.findAll().size();
        assertEquals(EXPECTED_CERTIFICATES_SIZE, actualSize);
    }

    @Test
    void findAllByParam() {
        int size = toTest.findAllBy(filterParams).size();
        assertEquals(2, size);
    }

    @Test
    void findAllByParamExpectedNoSuchParameterExceptionWhenParamNoExist() {
        assertThrows(NoSuchParameterException.class, () -> {
            toTest.findAllBy(wrongFilterParams);
        });
    }
}