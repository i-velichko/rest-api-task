package com.epam.esm.dao.impl;

import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
class GiftCertificateDaoImplTest {
    private static final int EXPECTED_FILTER_CERTIFICATES_LIST_SIZE_WHEN_PARAMS_NOT_EXIST = 0;
    private final GiftCertificateDaoImpl toTest;
    private static GiftCertificate giftCertificate;
    private static Map<String, String> filterParams;

    private static final long CERTIFICATE_ID = 1L;
    private static final long NOT_EXIST_ID = 5L;
    private static final String NEW_NAME = "newName";
    private static final String NOT_EXIST_NAME = "newName";
    private static final String NEW_DESCRIPTION = "newDescription";
    private static final BigDecimal NEW_PRICE = BigDecimal.valueOf(22);
    private static final int NEW_DURATION = 5;
    private static final int EXPECTED_CERTIFICATES_LIST_SIZE = 4;
    private static final int EXPECTED_FILTER_CERTIFICATES_LIST_SIZE = 2;

    @Autowired
    public GiftCertificateDaoImplTest(GiftCertificateDaoImpl certificateDao) {
        this.toTest = certificateDao;
    }

    @BeforeEach
    void setUp() {
        giftCertificate = new GiftCertificate(NEW_NAME, NEW_DESCRIPTION, NEW_PRICE,
                LocalDateTime.now(), LocalDateTime.now(), NEW_DURATION);

        filterParams = new HashMap<>();
        filterParams.put("tag", "epam");
        filterParams.put("search", "certificate");
    }

    @Test
    void create() {
        GiftCertificate actual = toTest.create(giftCertificate);
        assertEquals(actual.getName(), NEW_NAME);
        assertEquals(actual.getDescription(), NEW_DESCRIPTION);
        assertEquals(actual.getPrice(), NEW_PRICE);
        assertEquals(actual.getDuration(), NEW_DURATION);
    }

    @Test
    void findAll() {
        int actualSize = toTest.findAll().size();
        assertEquals(EXPECTED_CERTIFICATES_LIST_SIZE, actualSize);
    }

    @Test
    void findByIdPositive() {
        Optional<GiftCertificate> actual = toTest.findById(CERTIFICATE_ID);
        assertTrue(actual.isPresent());
    }

    @Test
    void findByIdNegativeWhenIdNotExist() {
        Optional<GiftCertificate> actual = toTest.findById(NOT_EXIST_ID);
        assertTrue(actual.isEmpty());
    }

    @Test
    void findByNamePositive() {
        Optional<GiftCertificate> actual = toTest.findByName(NEW_NAME);
        assertTrue(actual.isPresent());
    }

    @Test
    void findByNameNegativeWhenNameNotPresent() {
        Optional<GiftCertificate> actual = toTest.findByName(NOT_EXIST_NAME);
        assertTrue(actual.isEmpty());
    }


    @Test
    void findAllByParamPositive() {
        int actualSize = toTest.findAllByParam(filterParams).size();
        assertEquals(EXPECTED_FILTER_CERTIFICATES_LIST_SIZE, actualSize);
    }

    @Test
    void findAllByParamPositiveNegative() {
        filterParams.put("tag", "IBA");
        int actualSize = toTest.findAllByParam(filterParams).size();
        assertEquals(EXPECTED_FILTER_CERTIFICATES_LIST_SIZE_WHEN_PARAMS_NOT_EXIST, actualSize);
    }


}