package com.alexandrecampos.devchallenge.controller;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.response.CnabFileResponse;
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
    CnabFileResponse upload(@RequestPart MultipartFile file);
}
