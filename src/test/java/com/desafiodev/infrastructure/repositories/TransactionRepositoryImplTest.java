package com.desafiodev.infrastructure.repositories;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.desafiodev.infrastructure.repositories.jpas.TransactionEntityJpaRepository;
import com.desafiodev.utils.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryImplTest {

  @Mock private TransactionEntityJpaRepository transactionEntityJpaRepository;

  private TransactionRepositoryImpl transactionRepositoryImpl;

  @BeforeEach
  void setUp() {
    when(transactionEntityJpaRepository.save(any())).thenReturn(any());
    transactionRepositoryImpl = new TransactionRepositoryImpl(transactionEntityJpaRepository);
  }

  @Test
  void save() {
    transactionRepositoryImpl.save(Fixture.getTransaction(), Fixture.getStore());
    verify(transactionEntityJpaRepository, times(1)).save(any());
  }
}
