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

    public static <T> GenericPage<T> buildPage(List<T> content, int page, int size) {
        boolean last = content.size() <= size;
        if (!last) {
            content.remove(content.size() - 1);
        }

        return GenericPage.<T>builder().page(page).size(size).content(content).last(last).build();
    }
}
