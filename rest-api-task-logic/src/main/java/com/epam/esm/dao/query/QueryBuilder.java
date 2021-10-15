package com.epam.esm.dao.query;

import org.springframework.stereotype.Component;

import java.util.Map;

import static com.epam.esm.mapper.ParamName.*;

/**
 * @author Ivan Velichko
 * @date 09.10.2021 18:02
 */

@Component
public class QueryBuilder {
    private static final String SPACE = " ";
    private static final String EMPTY_LINE = "";
    private static final String PERCENT = "%";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String SEARCH_SQL = "WHERE t.name ILIKE '%s' and (c.name ILIKE '%s' " +
            "or c.description ILIKE '%s') ";

    public String buildQueryForSearch(Map<String, String> params) {
        String tag = params.get(TAG);
        if (tag == null) {
            tag = EMPTY_LINE;
        }
        String search = params.get(SEARCH);
        if (search == null) {
            search = EMPTY_LINE;
        }
        String order = params.get(ORDER);
        if (order == null) {
            order = EMPTY_LINE;
        }
        String query = String.format(params.get(QUERY) + SEARCH_SQL, PERCENT + tag
                + PERCENT, PERCENT + search + PERCENT, PERCENT + search + PERCENT);
        StringBuilder sb = new StringBuilder(query);

        if (params.get(SORT) != null && !params.get(SORT).isEmpty()) {
            sb.append(ORDER_BY).append(params.get(SORT)).append(SPACE).append(order);
        }
        return sb.toString();
    }
}
