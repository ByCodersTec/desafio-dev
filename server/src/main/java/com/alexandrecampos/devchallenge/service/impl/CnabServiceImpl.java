package com.alexandrecampos.devchallenge.service.impl;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.exception.InvalidParameterException;
import com.alexandrecampos.devchallenge.repository.CnabRepository;
import com.alexandrecampos.devchallenge.service.CnabService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    @Override
    public List<CnabDto> upload(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            List<String> list = br.lines().collect(Collectors.toList());
            return null;
        } catch (IOException ioException) {
            throw new InvalidParameterException("There was an error while reading the file");

        }
    }
}
