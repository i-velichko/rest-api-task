package com.epam.esm.controller;

import com.epam.esm.converter.BaseEntityConverter;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 16:41
 */

@RestController
@RequestMapping("/v1/certificates")
public class GiftCertificateController {
    private final BaseEntityConverter giftCertificateConverter;
    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(@Qualifier("giftCertificateConverter") BaseEntityConverter giftCertificateConverter
            , GiftCertificateService giftCertificateService) {
        this.giftCertificateConverter = giftCertificateConverter;
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificateDto> showAll() {
        List<GiftCertificate> certificates = giftCertificateService.findAll();
        return giftCertificateConverter.convertAllToDtoList(certificates);
    }
}
