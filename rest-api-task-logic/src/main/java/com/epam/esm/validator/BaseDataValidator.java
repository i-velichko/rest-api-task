package com.epam.esm.validator;

import com.epam.esm.entity.BaseEntity;

/**
 * @author Ivan Velichko
 * @date 08.10.2021 10:15
 */
public interface BaseDataValidator<T extends BaseEntity> {
    T checkDataForUpdate(T toUpdate, T previous);
}
