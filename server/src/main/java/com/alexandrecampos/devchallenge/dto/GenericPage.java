package com.alexandrecampos.devchallenge.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GenericPage<T> {
    private List<T> content;
    private int page;
    private int size;
    private boolean last;
    private int totalElements;
    private int totalPages;
}
