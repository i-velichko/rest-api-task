//package com.epam.esm.mapper.impl;
//
//import com.epam.esm.mapper.BaseEntityConverter;
//import com.epam.esm.dto.GiftCertificateDto;
//import com.epam.esm.entity.GiftCertificate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author Ivan Velichko
// * @date 04.10.2021 18:54
// */
//
//@Component
//public class GiftCertificateConverter implements BaseEntityConverter<GiftCertificate, GiftCertificateDto> {
//
//    @Override
//    public GiftCertificateDto convertToDto(GiftCertificate certificate) {
//        return new GiftCertificateDto(
//                certificate.getId(),
//                certificate.getName(),
//                certificate.getDescription(),
//                certificate.getPrice(),
//                certificate.getCreateDate(),
//                certificate.getLastUpdateDate(),
//                certificate.getDuration(),
//                certificate.getTags()
//        );
//    }
//
//    @Override
//    public List<GiftCertificateDto> convertAllToDtoList(List<GiftCertificate> certificates) {
//        return certificates.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public GiftCertificate convertToEntity(GiftCertificateDto giftCertificateDto) {
//        return new GiftCertificate(
//                giftCertificateDto.getId(),
//                giftCertificateDto.getName(),
//                giftCertificateDto.getDescription(),
//                giftCertificateDto.getPrice(),
//                giftCertificateDto.getCreateDate(),
//                giftCertificateDto.getLastUpdateDate(),
//                giftCertificateDto.getDuration(),
//                giftCertificateDto.getTags()
//        );
//    }
//
//    @Override
//    public List<GiftCertificate> convertAllToEntityList(List<GiftCertificateDto> list) {
//        return list.stream().map(this::convertToEntity).collect(Collectors.toList());
//    }
//}
