package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto findCertificateById(@PathVariable("id") long id) {
        return giftCertificateService.findBy(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<GiftCertificateDto> findAll(
            @RequestParam(required = false, name = "tag") String tag,
            @RequestParam(required = false, name = "search") String search,
            @RequestParam(required = false, name = "sort") String sort,
            @RequestParam(required = false, name = "order", defaultValue = "ASC") String order) {
        if (ObjectUtils.allNull(tag, search, sort)) {
            return giftCertificateService.findAll();
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("tag", tag);
            params.put("search", search);
            params.put("sort", sort);
            params.put("order", order);
            return giftCertificateService.findAllBy(params);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDto create(@RequestBody @Valid GiftCertificateDto certificateDto) {
        return giftCertificateService.create(certificateDto);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto update(@RequestBody @Valid GiftCertificateDto giftCertificateDto) {
        return giftCertificateService.update(giftCertificateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        giftCertificateService.delete(id);
    }
}
