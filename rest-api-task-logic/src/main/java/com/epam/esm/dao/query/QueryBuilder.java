package com.epam.esm.dao.query;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Ivan Velichko
 * @date 09.10.2021 18:02
 */

@Component
public class QueryBuilder {
    private static final String SPACE = " ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String SEARCH = "WHERE t.name ILIKE '%s' and (c.name ILIKE '%s' " +
            "or c.description ILIKE '%s') ";

    public String buildQueryForSearch(Map<String, String> params) {
        String tag = params.get("tag");
        if (tag == null) {
            tag = "";
        }
        String search = params.get("search");
        if (search == null) {
            search = "";
        }
        String order = params.get("order");
        if (order == null) {
            order = "";
        }
        String query = String.format(params.get("query") + SEARCH, "%" + tag
                + "%", "%" + search + "%", "%" + search + "%");
        StringBuilder sb = new StringBuilder(query);

        if (params.get("sort") != null && !params.get("sort").isEmpty()) {
            sb.append(ORDER_BY).append(params.get("sort")).append(SPACE).append(order);
        }
        return sb.toString();
    }
}
