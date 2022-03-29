package com.alexandrecampos.devchallenge.repository.jdbc;

import com.alexandrecampos.devchallenge.dto.CnabDto;
import com.alexandrecampos.devchallenge.dto.GenericPage;
import com.alexandrecampos.devchallenge.request.CnabListRequest;

public interface CnabRepositoryJdbc {
    GenericPage<CnabDto> list(CnabListRequest request);
}
