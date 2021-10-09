package com.epam.esm.validator.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Ivan Velichko
 * @date 08.10.2021 10:16
 */

@Component
public class GiftCertificateDataValidator {
    private TagDataValidator tagDataValidator;

    private static final int NAME_MAX_LENGTH = 100;
    private static final int NAME_MIN_LENGTH = 1;
    private static final int DESCRIPTION_MAX_LENGTH = 200;
    private static final int DESCRIPTION_MIN_LENGTH = 1;
    private static final BigDecimal PRICE_MIN_VALUE = BigDecimal.ONE;
    private static final BigDecimal PRICE_MAX_VALUE = new BigDecimal(Integer.MAX_VALUE);
    private static final int DURATION_MIN_VALUE = 1;
    private static final int DURATION_MAX_VALUE = 365;

    @Autowired
    public GiftCertificateDataValidator(TagDataValidator tagDataValidator) {
        this.tagDataValidator = tagDataValidator;
    }

    public boolean checkDataForCreateCertificate(GiftCertificate giftCertificate) {
        List<Object> fieldsToCreate = new ArrayList<>();
        GiftCertificate toCreate = new GiftCertificate();
        String name = giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        int duration = giftCertificate.getDuration();
        Set<Tag> tags = giftCertificate.getTags();

        if (name != null && (name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH)) {
            giftCertificate.setName(name);
            fieldsToCreate.add(name);
        }

        if (description != null && (description.length() >= DESCRIPTION_MIN_LENGTH && description.length() <= DESCRIPTION_MAX_LENGTH)) {
            giftCertificate.setDescription(description);
            fieldsToCreate.add(description);
        }

        if (price != null && (price.compareTo(PRICE_MIN_VALUE) >= 0 && price.compareTo(PRICE_MAX_VALUE) <= 0)) {
            giftCertificate.setPrice(price);
            fieldsToCreate.add(price);
        }

        if ((duration >= DURATION_MIN_VALUE && duration <= DURATION_MAX_VALUE)) {
            giftCertificate.setDuration(duration);
            fieldsToCreate.add(duration);
        }

        if (tags != null) {
            tags.stream().filter(tag -> tagDataValidator.isValid(tag)).forEach(tag -> toCreate.getTags().add(tag));
            fieldsToCreate.add(tags);
        }

        return fieldsToCreate.size() > 0;
    }

    public GiftCertificate checkDataForUpdateCertificate(GiftCertificate toUpdate, GiftCertificate previous) {
        GiftCertificate giftCertificate = new GiftCertificate();
        String name = toUpdate.getName();
        String description = toUpdate.getDescription();
        BigDecimal price = toUpdate.getPrice();
        int duration = toUpdate.getDuration();
        Set<Tag> tags = toUpdate.getTags();

        String previousName = previous.getName();
        String previousDescription = previous.getDescription();
        BigDecimal previousPrice = previous.getPrice();
        int previousDuration = previous.getDuration();
        Set<Tag> previousTags = previous.getTags();

        if (name != null && !name.equals(previousName)
                && (name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH)) {
            giftCertificate.setName(name);
        }

        if (description != null && !description.equals(previousDescription)
                && (description.length() >= DESCRIPTION_MIN_LENGTH && description.length() <= DESCRIPTION_MAX_LENGTH)) {
            giftCertificate.setDescription(description);
        }

        if (price != null && !price.equals(previousPrice)
                && (price.compareTo(PRICE_MIN_VALUE) >= 0 && price.compareTo(PRICE_MAX_VALUE) <= 0)) {
            giftCertificate.setPrice(price);
        }

        if (duration != previousDuration && (duration >= DURATION_MIN_VALUE && duration <= DURATION_MAX_VALUE)) {
            giftCertificate.setDuration(duration);
        }

        if (tags != null) {
            tags.stream().filter(tag -> tagDataValidator.isValid(tag)).forEach(previousTags::add);
        }

        return previous;
    }
}
