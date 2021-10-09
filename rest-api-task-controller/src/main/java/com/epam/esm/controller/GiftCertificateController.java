package com.epam.esm.controller;

import com.epam.esm.converter.impl.GiftCertificateConverter;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 16:41
 */

@RestController
@RequestMapping("/v1/certificates")
public class GiftCertificateController {
    private final GiftCertificateConverter giftCertificateConverter;
    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateConverter giftCertificateConverter
            , GiftCertificateService giftCertificateService) {
        this.giftCertificateConverter = giftCertificateConverter;
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping(value = "/{id}")
    public GiftCertificateDto findCertificateById(@PathVariable("id") long id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        return giftCertificateConverter.convertToDto(giftCertificate);
    }

    @GetMapping
    public List<GiftCertificateDto> showAll() {
        List<GiftCertificate> certificates = giftCertificateService.findAll();
        return giftCertificateConverter.convertAllToDtoList(certificates);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificate create(@RequestBody GiftCertificateDto certificateDto) {
        GiftCertificate giftCertificate = giftCertificateConverter.convertToEntity(certificateDto);
        return giftCertificateService.create(giftCertificate);
    }

    @PatchMapping
    public void update(@RequestBody GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = giftCertificateConverter.convertToEntity(giftCertificateDto);
        giftCertificateService.update(giftCertificate);
    }
}
