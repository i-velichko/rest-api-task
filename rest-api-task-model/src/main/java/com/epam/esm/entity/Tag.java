package com.epam.esm.entity;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 16:47
 */
public class Tag extends BaseEntity {
    private String name;

    public Tag(long id, String name) {
        super(id);
        this.name = name;
    }

    public Tag() {

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
        if (!super.equals(o)) {
            return false;
        }

        Tag tag = (Tag) o;

        return getName() != null ? getName().equals(tag.getName()) : tag.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

}
