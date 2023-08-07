package br.com.bycoders.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.bycoders.Mocks;
import br.com.bycoders.dto.OperacoesDTO;
import br.com.bycoders.repository.LojaRepository;
import br.com.bycoders.repository.TipoTransacoesRepository;
import br.com.bycoders.repository.TransacoesRepository;

@SpringBootTest
class TransacoesServiceTest {

	@Autowired
	private TransacoesService service;
	
	@MockBean
	private TransacoesRepository transacoesRepository;
	
	@MockBean
	private LojaRepository lojaRepository;
	
	@MockBean
	private TipoTransacoesRepository tipoRepository;
	
	@Test
	void deveFazerUploadComSucesso() {
		
		when(transacoesRepository.save(Mockito.any())).thenReturn(Mocks.getTransacoesUnico());
		when(lojaRepository.findById(Mockito.anyString())).thenReturn(Mocks.getOptionalLoja());
		when(tipoRepository.findById(1)).thenReturn(Mocks.getOptionalTipoTransacaoCredito());
		when(tipoRepository.findById(2)).thenReturn(Mocks.getOptionalTipoTransacaoDebito());
		when(transacoesRepository.findAllByIdLoja(Mockito.anyList())).thenReturn(Mocks.getListLojas());
		when(transacoesRepository.findAllByIdAndLoja(Mockito.anyList(), Mockito.any())).thenReturn(Mocks.getListTransacoes());
	
		when(lojaRepository.getTotalCredito(Mockito.any())).thenReturn(BigDecimal.valueOf(294.0));
		when(lojaRepository.getTotalDebito(Mockito.any())).thenReturn(BigDecimal.valueOf(112.0));
	
		List<OperacoesDTO> operacoes = service.upload(Mocks.getMockFile());
		
		assertEquals("BAR DO JOÃO       ", operacoes.get(0).getNomeLoja());
		assertEquals("JOÃO MACEDO   ", operacoes.get(0).getDonoLoja());
		assertEquals("4753****3153", operacoes.get(0).getTransacoes().get(0).getCartao());
		assertEquals("09620676017", operacoes.get(0).getTransacoes().get(0).getCpf());
		assertEquals(LocalDate.of(2019, 3, 1), operacoes.get(0).getTransacoes().get(0).getData());
		assertEquals(Long.valueOf(424l), operacoes.get(0).getTransacoes().get(0).getId());
		assertEquals(LocalTime.of(15, 34, 53), operacoes.get(0).getTransacoes().get(0).getHora());
		assertEquals("1 - Entrada - Débito", operacoes.get(0).getTransacoes().get(0).getTipo());
		assertEquals(BigDecimal.valueOf(142.0), operacoes.get(0).getTransacoes().get(0).getValor());
		
	}
	
	@Test
	void deveFazerUploadComSucessoLojaNaoExiste() {
		when(transacoesRepository.save(Mockito.any())).thenReturn(Mocks.getTransacoesUnico());
		when(lojaRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(tipoRepository.findById(1)).thenReturn(Mocks.getOptionalTipoTransacaoCredito());
		when(tipoRepository.findById(2)).thenReturn(Mocks.getOptionalTipoTransacaoDebito());
		when(transacoesRepository.findAllByIdLoja(Mockito.anyList())).thenReturn(Mocks.getListLojas());
		when(transacoesRepository.findAllByIdAndLoja(Mockito.anyList(), Mockito.any())).thenReturn(Mocks.getListTransacoes());
	
		when(lojaRepository.getTotalCredito(Mockito.any())).thenReturn(BigDecimal.valueOf(294.0));
		when(lojaRepository.getTotalDebito(Mockito.any())).thenReturn(BigDecimal.valueOf(112.0));
	
		List<OperacoesDTO> operacoes = service.upload(Mocks.getMockFile());
		
		assertEquals("BAR DO JOÃO       ", operacoes.get(0).getNomeLoja());
		assertEquals("JOÃO MACEDO   ", operacoes.get(0).getDonoLoja());
		assertEquals("4753****3153", operacoes.get(0).getTransacoes().get(0).getCartao());
		assertEquals("09620676017", operacoes.get(0).getTransacoes().get(0).getCpf());
		assertEquals(LocalDate.of(2019, 3, 1), operacoes.get(0).getTransacoes().get(0).getData());
		assertEquals(Long.valueOf(424l), operacoes.get(0).getTransacoes().get(0).getId());
		assertEquals(LocalTime.of(15, 34, 53), operacoes.get(0).getTransacoes().get(0).getHora());
		assertEquals("1 - Entrada - Débito", operacoes.get(0).getTransacoes().get(0).getTipo());
		assertEquals(BigDecimal.valueOf(142.0), operacoes.get(0).getTransacoes().get(0).getValor());
		
	}
	
	@Test
	void deveFazerUploadComSucessoSemSaldo() {
		when(transacoesRepository.save(Mockito.any())).thenReturn(Mocks.getTransacoesUnico());
		when(lojaRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(tipoRepository.findById(1)).thenReturn(Mocks.getOptionalTipoTransacaoCredito());
		when(tipoRepository.findById(2)).thenReturn(Mocks.getOptionalTipoTransacaoDebito());
		when(transacoesRepository.findAllByIdLoja(Mockito.anyList())).thenReturn(Mocks.getListLojas());
		when(transacoesRepository.findAllByIdAndLoja(Mockito.anyList(), Mockito.any())).thenReturn(Mocks.getListTransacoes());
	
		when(lojaRepository.getTotalCredito(Mockito.any())).thenReturn(null);
		when(lojaRepository.getTotalDebito(Mockito.any())).thenReturn(null);
	
		List<OperacoesDTO> operacoes = service.upload(Mocks.getMockFile());
		
		assertEquals("BAR DO JOÃO       ", operacoes.get(0).getNomeLoja());
		assertEquals("JOÃO MACEDO   ", operacoes.get(0).getDonoLoja());
		assertEquals("4753****3153", operacoes.get(0).getTransacoes().get(0).getCartao());
		assertEquals("09620676017", operacoes.get(0).getTransacoes().get(0).getCpf());
		assertEquals(LocalDate.of(2019, 3, 1), operacoes.get(0).getTransacoes().get(0).getData());
		assertEquals(Long.valueOf(424l), operacoes.get(0).getTransacoes().get(0).getId());
		assertEquals(LocalTime.of(15, 34, 53), operacoes.get(0).getTransacoes().get(0).getHora());
		assertEquals("1 - Entrada - Débito", operacoes.get(0).getTransacoes().get(0).getTipo());
		assertEquals(BigDecimal.valueOf(142.0), operacoes.get(0).getTransacoes().get(0).getValor());
		
	}
	
	@Test
	void vaiCairNaException() {
		when(transacoesRepository.save(Mockito.any())).thenReturn(Mocks.getTransacoesUnico());
		when(lojaRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(tipoRepository.findById(1)).thenReturn(Optional.empty());		
		when(transacoesRepository.findAllByIdLoja(Mockito.anyList())).thenReturn(Mocks.getListLojas());
		when(transacoesRepository.findAllByIdAndLoja(Mockito.anyList(), Mockito.any())).thenReturn(Mocks.getListTransacoes());
	
		when(lojaRepository.getTotalCredito(Mockito.any())).thenReturn(null);
		when(lojaRepository.getTotalDebito(Mockito.any())).thenReturn(null);
	
		List<OperacoesDTO> upload = service.upload(Mocks.getMockFile());
		
		assertNotNull(upload);
			
	}


}
