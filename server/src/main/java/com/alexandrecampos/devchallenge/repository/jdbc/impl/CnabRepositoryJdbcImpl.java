package com.alexandrecampos.devchallenge.repository.jdbc.impl;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.dto.GenericPage;
import com.alexandrecampos.devchallenge.repository.jdbc.CnabRepositoryJdbc;
import com.alexandrecampos.devchallenge.request.CnabListRequest;
import com.alexandrecampos.devchallenge.util.QueryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class CnabRepositoryJdbcImpl implements CnabRepositoryJdbc {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ObjectMapper objectMapper;

    public CnabRepositoryJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ObjectMapper objectMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public GenericPage<CnabDto> list(CnabListRequest request) {
        return QueryUtil.buildPage(Objects.requireNonNull(namedParameterJdbcTemplate.query(
                        QueryUtil.getQuery("cnab/list"),
                        new MapSqlParameterSource()
                                .addValue(CnabListRequest.Fields.CARD_NUMBER.name(), request.getCardNumber())
                                .addValue(CnabListRequest.Fields.DOCUMENT_ID.name(), request.getDocumentId())
                                .addValue(CnabListRequest.Fields.PAGE.name(), request.getPage())
                                .addValue(CnabListRequest.Fields.SIZE.name(), request.getSize()),
                        JdbcTemplateMapperFactory
                                .newInstance()
                                .newResultSetExtractor(CnabDto.class)
                )), request.getPage(), request.getSize()
        );
    }
}
