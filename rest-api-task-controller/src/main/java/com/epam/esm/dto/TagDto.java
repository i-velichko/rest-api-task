package com.epam.esm.dto;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:42
 */
public class TagDto extends BaseDto {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagDto{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
