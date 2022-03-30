package com.alexandrecampos.devchallenge.service.impl;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.dto.GenericPage;
import com.alexandrecampos.devchallenge.dto.InvalidCnabField;
import com.alexandrecampos.devchallenge.exception.InvalidParameterException;
import com.alexandrecampos.devchallenge.model.Cnab;
import com.alexandrecampos.devchallenge.model.CnabSummaryDto;
import com.alexandrecampos.devchallenge.repository.CnabRepository;
import com.alexandrecampos.devchallenge.request.CnabListRequest;
import com.alexandrecampos.devchallenge.response.CnabFileResponse;
import com.alexandrecampos.devchallenge.service.CnabService;
import com.alexandrecampos.devchallenge.util.CnabUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class CnabServiceImpl implements CnabService {
    private final CnabRepository cnabRepository;
    private final ObjectMapper objectMapper;

    public CnabServiceImpl(CnabRepository cnabRepository, ObjectMapper objectMapper) {
        this.cnabRepository = cnabRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public GenericPage<CnabDto> list(CnabListRequest request) {
        return cnabRepository.list(request);
    }

    @Override
    public CnabFileResponse upload(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            List<String> list = br.lines().collect(Collectors.toList());

            List<InvalidCnabField> invalidLines = IntStream.range(0, list.size())
                    .filter(i -> CnabUtil.invalidField(list.get(i)) != null)
                    .mapToObj(i -> CnabUtil.invalidField(list.get(i), i))
                    .collect(Collectors.toList());

            List<Cnab> validCnabs = list.stream().filter(it -> CnabUtil.invalidField(it) == null).map(CnabUtil::stringToCnab).collect(Collectors.toList());

            List<CnabDto> savedCnabs = cnabRepository.saveAll(validCnabs).stream().map(this::convertCnab).collect(Collectors.toList());

            return CnabFileResponse.builder().savedCnabs(savedCnabs).invalidLines(invalidLines).build();
        } catch (IOException ioException) {
            throw new InvalidParameterException("There was an error while reading the file");
        }
    }

    @Override
    public CnabSummaryDto summary(String documentId, String cardNumber) {
        return cnabRepository.summary(documentId, cardNumber);
    }

    private CnabDto convertCnab(Cnab cnab) {
        return objectMapper.convertValue(cnab, CnabDto.class);
    }
}
