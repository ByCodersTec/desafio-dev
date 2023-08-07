package br.com.bycoders.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TipoTransacaoTest {

	@Test
	void testGetter() {
		TipoTransacao tp = new TipoTransacao();
		tp.setOperacao(Operacao.SOMAR);
		
		assertEquals(Operacao.SOMAR, tp.getOperacao());
	}

}
