package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 16:47
 */
public class GiftCertificate extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private ZonedDateTime create_date;
    private ZonedDateTime last_update_date;
    private int duration;

    public GiftCertificate(long id, String name, String description, BigDecimal price, ZonedDateTime create_date, ZonedDateTime last_update_date, int duration) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.create_date = create_date;
        this.last_update_date = last_update_date;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZonedDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(ZonedDateTime create_date) {
        this.create_date = create_date;
    }

    public ZonedDateTime getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(ZonedDateTime last_update_date) {
        this.last_update_date = last_update_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

        GiftCertificate that = (GiftCertificate) o;

        if (getDuration() != that.getDuration()) {
            return false;
        }
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null) {
            return false;
        }
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null) {
            return false;
        }
        if (getCreate_date() != null ? !getCreate_date().equals(that.getCreate_date()) : that.getCreate_date() != null) {
            return false;
        }
        return getLast_update_date() != null ? getLast_update_date().equals(that.getLast_update_date()) : that.getLast_update_date() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCreate_date() != null ? getCreate_date().hashCode() : 0);
        result = 31 * result + (getLast_update_date() != null ? getLast_update_date().hashCode() : 0);
        result = 31 * result + getDuration();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GiftCertificate{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", create_date=").append(create_date);
        sb.append(", last_update_date=").append(last_update_date);
        sb.append(", duration=").append(duration);
        sb.append('}');
        return sb.toString();
    }
}
