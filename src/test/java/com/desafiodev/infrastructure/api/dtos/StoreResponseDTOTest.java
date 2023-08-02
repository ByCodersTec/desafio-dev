package com.desafiodev.infrastructure.api.dtos;

import static com.desafiodev.utils.Fixture.getStore;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class StoreResponseDTOTest {

  @Test
  void testClass() {
    EqualsVerifier.forClass(StoreResponseDTO.class).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(StoreResponseDTO.class).verify();
  }

  @Test
  void asList() {
    List<StoreResponseDTO> list =
        StoreResponseDTO.asList(singletonList(StoreEntity.from(getStore())));
    StoreResponseDTO dto = list.stream().findFirst().orElseThrow();
    assertNotNull(dto.getStoreId());
    assertEquals("NAME", dto.getStoreName());
    assertEquals("OWNER NAME", dto.getOwnerName());
    assertEquals(0.0, dto.getStoreBalance());
  }
}
