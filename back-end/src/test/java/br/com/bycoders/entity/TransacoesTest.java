package br.com.bycoders.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransacoesTest {

	@Test
	void testGetter() {
		Loja loja = new Loja();
		loja.setNomeLoja("teste");
		
		Transacoes t1 = new Transacoes();
		t1.setLoja(loja);
		
		assertEquals(loja.getNomeLoja(), t1.getLoja().getNomeLoja());
		
	}

}
