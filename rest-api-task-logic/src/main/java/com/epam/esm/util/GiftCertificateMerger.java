package com.epam.esm.util;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.nonNull;

/**
 * @author Ivan Velichko
 * @date 08.10.2021 10:51
 */
public class GiftCertificateMerger {
    public static void merge(GiftCertificate toUpdate, GiftCertificate previous) {

        String name = toUpdate.getName();
        String description = toUpdate.getDescription();
        BigDecimal price = toUpdate.getPrice();
        LocalDateTime lastUpdateDate = toUpdate.getLastUpdateDate();
        int duration = toUpdate.getDuration();
        Set<Tag> tags = toUpdate.getTags();

        if (name != null) {
            previous.setName(name);
        }
        if (description != null) {
            previous.setDescription(description);
        }
        if (price != null) {
            previous.setPrice(price);
        }
        if (lastUpdateDate != null) {
            previous.setLastUpdateDate(lastUpdateDate);
        }
        if (duration != 0) {
            previous.setDuration(duration);
        }

        if (tags.size() != 0) {
            tags.stream().filter(Objects::nonNull).forEach(tag -> previous.getTags().add(tag));
        }


    }
}
