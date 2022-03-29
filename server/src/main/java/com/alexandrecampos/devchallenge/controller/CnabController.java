package com.alexandrecampos.devchallenge.controller;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("cnab")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface CnabController {
    @GetMapping
    List<CnabDto> list();
}
