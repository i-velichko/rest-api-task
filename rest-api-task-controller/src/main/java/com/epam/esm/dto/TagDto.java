package com.epam.esm.dto;

/**
 * @author Ivan Velichko
 * @date 03.10.2021 15:42
 */
public class TagDto extends BaseDto{
    private String name;

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
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagDto{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
