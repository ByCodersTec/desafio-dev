package com.alexandrecampos.devchallenge.service;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.dto.GenericPage;
import com.alexandrecampos.devchallenge.model.CnabSummaryDto;
import com.alexandrecampos.devchallenge.request.CnabListRequest;
import com.alexandrecampos.devchallenge.response.CnabFileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CnabService {
    GenericPage<CnabDto> list(CnabListRequest request);

    CnabFileResponse upload(MultipartFile file);

    CnabSummaryDto summary(String documentId, String cardNumber);
}
