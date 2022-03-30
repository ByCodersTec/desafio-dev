package com.alexandrecampos.devchallenge.util;

import com.alexandrecampos.devchallenge.dto.GenericPage;

import java.util.List;

public final class QueryUtil {
    private static final String QUERY_PATH = "db/query/%s.sql";

    private QueryUtil() {
    }

    public static String getQuery(String queryName) {
        return LoadResourceUtil.getResource(String.format(QUERY_PATH, queryName));
    }

    public static <T> GenericPage<T> buildPage(List<T> content, int total, int page, int size) {
        int totalPages = (int) Math.ceil(1.0 * total / size);
        return GenericPage.<T>builder().page(page).size(size).content(content).last(page + 1 >= totalPages).totalElements(total).totalPages(totalPages).build();
    }
}
