//package com.epam.esm.service.impl;
//
//import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
//import com.epam.esm.dao.impl.TagDaoImpl;
//import com.epam.esm.dto.GiftCertificateDto;
//import com.epam.esm.dto.TagDto;
//import com.epam.esm.entity.GiftCertificate;
//import com.epam.esm.entity.Tag;
//import com.epam.esm.exception.DuplicateEntityException;
//import com.epam.esm.exception.NoSuchEntityException;
//import com.epam.esm.mapper.GiftCertificateMapper;
//import com.epam.esm.validator.impl.GiftCertificateDataValidator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//import static java.math.BigDecimal.valueOf;
//import static java.time.LocalDateTime.now;
//import static java.util.Collections.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//class GiftCertificateServiceImplTest {
//    private static final int EXPECTED_CERTIFICATES_SIZE = 3;
//    private GiftCertificateServiceImpl toTest;
//    private Map<String, String> filterParams;
//    private GiftCertificateDto certificate1;
//    private GiftCertificateDto certificate2;
//    private GiftCertificateDto certificate3;
//    private GiftCertificateDto certificate4;
//    private GiftCertificateDto newCertificate;
//
//    @Mock
//    private GiftCertificateDaoImpl giftCertificateDao;
//    @Mock
//    private GiftCertificateDataValidator giftCertificateDataValidator;
//    @Mock
//    private TagDaoImpl tagDao;
//    @Mock
//    private GiftCertificateMapper mapper;
//
//    @BeforeEach
//    void setUp() {
//        openMocks(this);
//
//        Set<TagDto> tags = Set.of(new TagDto(1, "tag1"), new TagDto(2, "tag2"), new TagDto(3, "tag3"));
//        certificate1 = new GiftCertificateDto(1, "certificate1", "description1", valueOf(10), now(), now(), 10, tags);
//        certificate2 = new GiftCertificateDto(2, "you cant find me with filter", "description2", valueOf(10), now(), now(), 10, tags);
//        certificate3 = new GiftCertificateDto(3, "certificate3", "description3", valueOf(10), now(), now(), 10, tags);
//        certificate4 = new GiftCertificateDto(4, "certificate4", "description4", valueOf(10), now(), now(), 10, tags);
//
//        newCertificate = new GiftCertificateDto();
//        newCertificate.setName("certificate4");
//        newCertificate.setDescription("description4");
//        newCertificate.setPrice(BigDecimal.valueOf(10));
//        newCertificate.setDuration(10);
//
//        filterParams = new HashMap<>();
//        filterParams.put("search", "certificate");
//
//        when(mapper.toDto(any())).thenReturn(certificate1);
////        when(mapper.toDto(any())).thenReturn(certificate2);
////        when(mapper.toDto(any())).thenReturn(certificate3);
////        when(mapper.toDto(any())).thenReturn(certificate4);
//        when(giftCertificateDao.findAll()).thenReturn(emptyList());
//        when(giftCertificateDao.findAllByParam(anyMap())).thenReturn(emptyList());
//        when(giftCertificateDao.findById(1)).thenReturn(Optional.of(new GiftCertificate()));
//        when(giftCertificateDataValidator.checkDataForCreateCertificate(any())).thenReturn(true);
//        when(giftCertificateDao.create(any())).thenReturn(any());
//
//        toTest = new GiftCertificateServiceImpl(giftCertificateDao, tagDao, giftCertificateDataValidator, mapper);
//    }
//
//    @Test
//    void findByIdPositive() {
//        GiftCertificateDto actualCertificate = toTest.findById(1);
//        assertEquals(certificate1, actualCertificate);
//    }
//
//    @Test
//    void findByIdExpectedExceptionWhenCertificateNotExist() {
//        assertThrows(NoSuchEntityException.class, () -> {
//            toTest.findById(2);
//        });
//    }
//
//    @Test
//    void findAll() {
//        int actualSize = toTest.findAll().size();
//        assertEquals(EXPECTED_CERTIFICATES_SIZE, actualSize);
//    }
//
//    @Test
//    void findAllByParam() {
//        int size = toTest.findAllByParam(filterParams).size();
//        assertEquals(2, size);
//    }
//
//    @Test
//    void createPositive() {
//        GiftCertificateDto expectedCertificate = toTest.create(newCertificate);
//        assertEquals("certificate4", expectedCertificate.getName());
//        assertEquals("description4", expectedCertificate.getDescription());
//        assertEquals(BigDecimal.valueOf(10), expectedCertificate.getPrice());
//        assertEquals(10, expectedCertificate.getDuration());
//    }
////
////    @Test
////    void createFalseWhenCertificateAlreadyExist() {
////        assertThrows(DuplicateEntityException.class, () -> {
////            toTest.create(newCertificate);
////        });
////    }
//
//}