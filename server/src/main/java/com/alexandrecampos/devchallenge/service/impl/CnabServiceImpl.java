package com.alexandrecampos.devchallenge.service.impl;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.repository.CnabRepository;
import com.alexandrecampos.devchallenge.service.CnabService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CnabServiceImpl implements CnabService {
    private final CnabRepository cnabRepository;
    private final ObjectMapper objectMapper;

    public CnabServiceImpl(CnabRepository cnabRepository, ObjectMapper objectMapper) {
        this.cnabRepository = cnabRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CnabDto> list() {
        return cnabRepository.findAll().stream().map(it -> objectMapper.convertValue(it, CnabDto.class)).collect(Collectors.toList());
    }
}
