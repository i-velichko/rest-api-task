package com.epam.esm.dao.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Velichko
 * @date 09.10.2021 18:03
 */
public enum GiftCertificateFieldType {
    ID("id"),

    NAME("name"),

    DESCRIPTION("description"),

    PRICE("price"),

    CREATE_DATE("create_date"),

    LAST_UPDATE_DATE("last_update_date"),

    DURATION("duration");

    private String name;

    GiftCertificateFieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<String> getFields() {
        List<String> fields = new ArrayList<>();
        GiftCertificateFieldType[] values = GiftCertificateFieldType.values();
        for (GiftCertificateFieldType next : values) {
            fields.add(next.getName());
        }
        return fields;
    }
}
