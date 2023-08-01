package com.desafiodev.infrastructure.api.dtos;

import static com.desafiodev.utils.Fixture.getStore;
import static com.desafiodev.utils.Fixture.getTransaction;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class TransactionResponseDTOTest {

  @Test
  void testClass() {
    EqualsVerifier.forClass(TransactionResponseDTO.class)
        .suppress(Warning.STRICT_INHERITANCE)
        .verify();
    ToStringVerifier.forClass(TransactionResponseDTO.class).verify();
  }

  @Test
  void asList() {
    List<TransactionResponseDTO> list =
        TransactionResponseDTO.asList(
            singletonList(TransactionEntity.from(getTransaction(), getStore())));
    TransactionResponseDTO dto = list.stream().findFirst().orElseThrow();
    assertNotNull(dto.getTransactionId());
    assertNotNull(dto.getStoreId());
    assertEquals("ALUGUEL", dto.getType());
    assertNotNull(dto.getDate());
    assertEquals(10.0, dto.getTransactionValue());
    assertEquals("11111111111", dto.getCpfNumber());
    assertEquals("111111111111", dto.getCreditCardNumber());
    assertEquals("NAME", dto.getStoreName());
    assertEquals("OWNER NAME", dto.getOwnerName());
    assertEquals(0.0, dto.getStoreBalance());
  }
}
