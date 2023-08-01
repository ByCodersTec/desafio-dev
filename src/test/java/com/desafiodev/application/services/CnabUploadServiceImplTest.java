package com.desafiodev.application.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.desafiodev.application.ports.out.StoreRepository;
import com.desafiodev.application.ports.out.TransactionRepository;
import com.desafiodev.utils.Fixture;
import java.io.File;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CnabUploadServiceImplTest {

  @Mock private TransactionRepository transactionRepository;
  @Mock private StoreRepository storeRepository;

  private CnabUploadServiceImpl cnabUploadService;

  @BeforeEach
  void setUp() {
    cnabUploadService = new CnabUploadServiceImpl(transactionRepository, storeRepository);
  }

  @Test
  void accept() {
    when(storeRepository.findByNameAndOwnerName(any(), any()))
        .thenReturn(Optional.of(Fixture.getStore()));
    doNothing().when(storeRepository).save(any());
    doNothing().when(transactionRepository).save(any(), any());
    cnabUploadService.accept(new File("src/test/resources/CNAB.txt"));
    verify(storeRepository, times(21)).findByNameAndOwnerName(any(), any());
    verify(storeRepository, times(21)).save(any());
    verify(transactionRepository, times(21)).save(any(), any());
  }

  @Test
  void acceptWithNoStory() {
    when(storeRepository.findByNameAndOwnerName(any(), any())).thenReturn(Optional.empty());
    doNothing().when(storeRepository).save(any());
    doNothing().when(transactionRepository).save(any(), any());
    cnabUploadService.accept(new File("src/test/resources/CNAB.txt"));
    verify(storeRepository, times(21)).findByNameAndOwnerName(any(), any());
    verify(storeRepository, times(21)).save(any());
    verify(transactionRepository, times(21)).save(any(), any());
  }

  @Test
  void acceptWithError() {
    assertThrows(
        IllegalStateException.class, () -> cnabUploadService.accept(new File("not_exist")));
    verify(transactionRepository, times(0)).save(any(), any());
  }
}
