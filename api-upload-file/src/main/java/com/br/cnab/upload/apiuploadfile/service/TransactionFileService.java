package com.br.cnab.upload.apiuploadfile.service;

import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface TransactionFileService {

    void processTransactionFile(MultipartFile file) throws IOException;

    List<StoreOperationResponse> listStoreOperations();

}
