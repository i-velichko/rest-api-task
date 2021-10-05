package com.epam.esm.dto;

import com.epam.esm.entity.Tag;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:50
 */
public class GiftCertificateDto extends BaseDto{

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime create_date;
    private LocalDateTime last_update_date;
    private int duration;
    private Set<Tag> tags;

    public GiftCertificateDto(long id, String name, String description, BigDecimal price, LocalDateTime create_date, LocalDateTime last_update_date, int duration, Set<Tag> tags) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.create_date = create_date;
        this.last_update_date = last_update_date;
        this.duration = duration;
        this.tags = tags;
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

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public LocalDateTime getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(LocalDateTime last_update_date) {
        this.last_update_date = last_update_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GiftCertificateDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", create_date=").append(create_date);
        sb.append(", last_update_date=").append(last_update_date);
        sb.append(", duration=").append(duration);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}
