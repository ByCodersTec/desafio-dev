package com.alexandrecampos.devchallenge.controller;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.dto.GenericPage;
import com.alexandrecampos.devchallenge.model.CnabSummaryDto;
import com.alexandrecampos.devchallenge.request.CnabListRequest;
import com.alexandrecampos.devchallenge.response.CnabFileResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("cnab")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface CnabController {
    @GetMapping
    GenericPage<CnabDto> list(CnabListRequest request);

    @GetMapping("summary")
    CnabSummaryDto summary(@RequestParam(required = false) String documentId, @RequestParam(required = false) String cardNumber);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CnabFileResponse upload(@RequestPart MultipartFile file);
}
