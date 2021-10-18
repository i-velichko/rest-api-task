package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static com.epam.esm.exception.CustomErrorMessageCode.*;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 18:50
 */
public class GiftCertificateDto extends BaseDto {

    @NotBlank(message = CERTIFICATE_NAME_INCORRECT)
    @Size(min = 1, max = 200, message = CERTIFICATE_NAME_INCORRECT)
    private String name;

    @Size(max = 200, message = CERTIFICATE_DESCRIPTION_INCORRECT)
    @NotNull(message = CERTIFICATE_DESCRIPTION_INCORRECT)
    private String description;

    @Positive(message = CERTIFICATE_PRICE_INCORRECT)
    @Digits(integer = 5, fraction = 2, message = CERTIFICATE_PRICE_INCORRECT)
    private BigDecimal price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime lastUpdateDate;

    @Positive(message = CERTIFICATE_DURATION_INCORRECT)
    @Min(1)
    @Max(180)
    @NotNull
    private int duration;
    private Set<TagDto> tags;

    public GiftCertificateDto() {
    }

    public GiftCertificateDto(long id, String name, String description, BigDecimal price, LocalDateTime createDate, LocalDateTime lastUpdateDate, int duration, Set<TagDto> tags) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GiftCertificateDto that = (GiftCertificateDto) o;

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
        if (getCreateDate() != null ? !getCreateDate().equals(that.getCreateDate()) : that.getCreateDate() != null) {
            return false;
        }
        if (getLastUpdateDate() != null ? !getLastUpdateDate().equals(that.getLastUpdateDate()) : that.getLastUpdateDate() != null) {
            return false;
        }
        return getTags() != null ? getTags().equals(that.getTags()) : that.getTags() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getLastUpdateDate() != null ? getLastUpdateDate().hashCode() : 0);
        result = 31 * result + getDuration();
        result = 31 * result + (getTags() != null ? getTags().hashCode() : 0);
        return result;
    }
}
