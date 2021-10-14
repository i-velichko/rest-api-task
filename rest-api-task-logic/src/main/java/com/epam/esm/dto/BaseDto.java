package com.epam.esm.dto;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:42
 */
public abstract class BaseDto {
    private long id;

    public BaseDto() {
    }

    public BaseDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
