package com.alexandrecampos.devchallenge.controller.impl;

import com.alexandrecampos.devchallenge.controller.CnabController;
import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.dto.GenericPage;
import com.alexandrecampos.devchallenge.request.CnabListRequest;
import com.alexandrecampos.devchallenge.response.CnabFileResponse;
import com.alexandrecampos.devchallenge.service.CnabService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CnabControllerImpl implements CnabController {
    private final CnabService cnabService;

    public CnabControllerImpl(CnabService cnabService) {
        this.cnabService = cnabService;
    }

    @Override
    public GenericPage<CnabDto> list(CnabListRequest request) {
        return cnabService.list(request);
    }

    @Override
    public CnabFileResponse upload(MultipartFile file) {
        return cnabService.upload(file);
    }
}
