package com.epam.esm.controller;

import com.epam.esm.converter.impl.GiftCertificateConverter;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping()
    public List<GiftCertificateDto> findAll(
            @RequestParam(required = false, name = "tag") String tag,
            @RequestParam(required = false, name = "search") String search,
            @RequestParam(required = false, name = "sort") String sort,
            @RequestParam(required = false, name = "order", defaultValue = "ASC") String order) {


        if (tag == null && search == null && sort == null && order == null) {

            List<GiftCertificate> giftCertificates = giftCertificateService.findAll();
            return giftCertificateConverter.convertAllToDtoList(giftCertificates);
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("tag", tag);
            params.put("search", search);
            params.put("sort", sort);
            params.put("order", order);
            List<GiftCertificate> allByParam = giftCertificateService.findAllByParam(params);
            return giftCertificateConverter.convertAllToDtoList(allByParam);
        }
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
