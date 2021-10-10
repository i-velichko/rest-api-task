package com.epam.esm.dao.impl;

import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
class GiftCertificateDaoImplTest {
    private final GiftCertificateDaoImpl certificateDao;
    private static GiftCertificate newGiftCertificate;

    private static final long CERTIFICATE_ID = 1L;

    @Autowired
    public GiftCertificateDaoImplTest(GiftCertificateDaoImpl certificateDao) {
        this.certificateDao = certificateDao;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        Optional<GiftCertificate> actual = certificateDao.findById(CERTIFICATE_ID);
        assertTrue(actual.isPresent());
    }

    @Test
    void findByName() {
    }

    @Test
    void update() {
    }

    @Test
    void findAllByParam() {
    }

    @Test
    void delete() {
    }
}