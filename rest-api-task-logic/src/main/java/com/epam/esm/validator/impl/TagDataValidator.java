package com.epam.esm.validator.impl;

import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

/**
 * @author Ivan Velichko
 * @date 09.10.2021 13:57
 */

@Component
public class TagDataValidator {
    private static final int NAME_MAX_LENGTH = 30;
    private static final int NAME_MIN_LENGTH = 1;

    public boolean isValid(Tag tag) {
        if (tag.getName() == null) {
            return false;
        }
        return tag.getName().length() >= NAME_MIN_LENGTH && tag.getName().length() <= NAME_MAX_LENGTH;
    }
}
