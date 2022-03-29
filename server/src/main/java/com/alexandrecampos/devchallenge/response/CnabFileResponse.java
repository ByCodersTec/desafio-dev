package com.alexandrecampos.devchallenge.response;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.dto.InvalidCnabField;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CnabFileResponse {
    private List<InvalidCnabField> invalidLines;
    private List<CnabDto> savedCnabs;
}
