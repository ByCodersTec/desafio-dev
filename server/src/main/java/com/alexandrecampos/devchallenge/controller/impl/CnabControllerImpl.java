package com.alexandrecampos.devchallenge.controller.impl;

import com.alexandrecampos.devchallenge.controller.CnabController;
import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.service.CnabService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CnabControllerImpl implements CnabController {
    private final CnabService cnabService;

    public CnabControllerImpl(CnabService cnabService) {
        this.cnabService = cnabService;
    }

    @Override
    public List<CnabDto> list() {
        return cnabService.list();
    }
}
