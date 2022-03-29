package com.alexandrecampos.devchallenge.controller;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("cnab")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface CnabController {
    @GetMapping
    List<CnabDto> list();

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<CnabDto> upload(@RequestPart MultipartFile file);
}
