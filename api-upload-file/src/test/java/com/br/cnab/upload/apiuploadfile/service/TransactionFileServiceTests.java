package com.br.cnab.upload.apiuploadfile.service;

import com.br.cnab.upload.apiuploadfile.builder.StoreOperationResponseBuilder;
import com.br.cnab.upload.apiuploadfile.builder.TransactionBuilder;
import com.br.cnab.upload.apiuploadfile.entity.Transaction;
import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;
import com.br.cnab.upload.apiuploadfile.repository.TransactionFileRepository;
import com.br.cnab.upload.apiuploadfile.service.impl.TransactionFileServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.br.cnab.upload.apiuploadfile.builder.StoreOperationResponseBuilder.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionFileServiceTests {

    @InjectMocks
    private TransactionFileServiceImpl transactionFileService;

    @Mock
    private TransactionFileRepository transactionFileRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessTransactionFile() throws IOException {
    }

    @Test
    public void testListStoreOperations() {
        List<StoreOperationResponse> storeOperationsList = storeOperationResponseBuilder().buildList();
    }

}
