package com.epam.esm.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.epam.esm.exception.CustomErrorMessageCode.TAG_NAME_INCORRECT;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:42
 */
public class TagDto extends BaseDto {
    @NotBlank(message = TAG_NAME_INCORRECT)
    @Size(min = 1, max = 30, message = TAG_NAME_INCORRECT)
    private String name;

    public TagDto() {
    }

    public TagDto(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TagDto tagDto = (TagDto) o;

        return getName() != null ? getName().equals(tagDto.getName()) : tagDto.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

}
