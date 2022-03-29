package com.alexandrecampos.devchallenge.service;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CnabService {
    List<CnabDto> list();

    List<CnabDto> upload(MultipartFile file);
}
