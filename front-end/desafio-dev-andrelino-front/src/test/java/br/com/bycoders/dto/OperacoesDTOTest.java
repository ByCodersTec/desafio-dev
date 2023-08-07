package br.com.bycoders.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OperacoesDTOTest {

	@Test
	void testGetSet() {
		OperacoesDTO op = new OperacoesDTO();
		op.setNomeLoja("Bar do João");
		op.setDonoLoja("João Apolinario");
		List<TransacoesDTO> lista = new ArrayList<>();
		TransacoesDTO transacoesDTO = new TransacoesDTO();
		transacoesDTO.setId(1l);
		transacoesDTO.setCartao("223xxx355");
		transacoesDTO.setCpf("185.646.223-99");
		transacoesDTO.setData(LocalDate.of(2023, 8, 5));
		transacoesDTO.setHora(LocalTime.of(10, 22, 15));
		transacoesDTO.setTipo("1 - Credito");
		transacoesDTO.setValor(BigDecimal.valueOf(1500.0));
		lista.add(transacoesDTO);
		op.setTransacoes(lista);		
		op.setSaldoConta(BigDecimal.ZERO);
		
		assertEquals("Bar do João", op.getNomeLoja());
		assertEquals("João Apolinario", op.getDonoLoja());
		assertEquals(BigDecimal.ZERO, op.getSaldoConta());
		assertNotNull(op.getTransacoes());
		assertEquals(1L, op.getTransacoes().get(0).getId());
		assertEquals("223xxx355", op.getTransacoes().get(0).getCartao());
		assertEquals("185.646.223-99", op.getTransacoes().get(0).getCpf());
		assertEquals(LocalDate.of(2023, 8, 5), op.getTransacoes().get(0).getData());
		assertEquals(LocalTime.of(10, 22, 15), op.getTransacoes().get(0).getHora());
		assertEquals("1 - Credito", op.getTransacoes().get(0).getTipo());
		assertEquals(BigDecimal.valueOf(1500.0), op.getTransacoes().get(0).getValor());
	}

}
