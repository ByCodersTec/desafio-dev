package com.desafiodev.infrastructure.repositories;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.jpas.StoreEntityJpaRepository;
import com.desafiodev.utils.Fixture;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StoreRepositoryImplTest {

  @Mock private StoreEntityJpaRepository storeEntityJpaRepository;

  private StoreRepositoryImpl storeRepository;

  @BeforeEach
  void setUp() {
    storeRepository = new StoreRepositoryImpl(storeEntityJpaRepository);
  }

  @Test
  void findByNameAndOwnerName() {
    when(storeEntityJpaRepository.findByNameIgnoreCaseAndOwnerNameIgnoreCase(any(), any()))
        .thenReturn(Optional.of(StoreEntity.from(Fixture.getStore())));
    storeRepository.findByNameAndOwnerName("STORE NAME", "OWNER NAME");
    verify(storeEntityJpaRepository, times(1))
        .findByNameIgnoreCaseAndOwnerNameIgnoreCase(any(), any());
  }

  @Test
  void findByNameAndOwnerNameEmpty() {
    when(storeEntityJpaRepository.findByNameIgnoreCaseAndOwnerNameIgnoreCase(any(), any()))
        .thenReturn(Optional.empty());
    storeRepository.findByNameAndOwnerName("STORE NAME", "OWNER NAME");
    verify(storeEntityJpaRepository, times(1))
        .findByNameIgnoreCaseAndOwnerNameIgnoreCase(any(), any());
  }

  @Test
  void save() {
    when(storeEntityJpaRepository.save(any())).thenReturn(StoreEntity.from(Fixture.getStore()));
    storeRepository.save(Fixture.getStore());
    verify(storeEntityJpaRepository, times(1)).save(any());
  }
}
