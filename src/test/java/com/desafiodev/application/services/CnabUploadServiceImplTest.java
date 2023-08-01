package com.desafiodev.application.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.desafiodev.application.ports.out.TransactionRepository;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CnabUploadServiceImplTest {

  @Mock private TransactionRepository transactionRepository;

  private CnabUploadServiceImpl cnabUploadService;

  @BeforeEach
  void setUp() {
    doNothing().when(transactionRepository).save(any());
    cnabUploadService = new CnabUploadServiceImpl(transactionRepository);
  }

  @Test
  void accept() {
    cnabUploadService.accept(new File("src/test/resources/CNAB.txt"));
    verify(transactionRepository, times(21)).save(any());
  }
}
