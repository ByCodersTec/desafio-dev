package com.alexandrecampos.devchallenge.service;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.response.CnabFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CnabService {
    List<CnabDto> list();

    CnabFileResponse upload(MultipartFile file);
}
