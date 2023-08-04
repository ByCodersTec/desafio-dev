package br.com.bycoders.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.bycoders.dto.OperacoesDTO;
import br.com.bycoders.service.TransacoesService;

@WebMvcTest(TransacoesController.class)
class TransacoesControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TransacoesService service;
	
	@Test
	void testUpload() throws Exception {
		URI uri = new URI("transacoes/cnab/upload");
		StringBuilder sb = new StringBuilder("3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       \n"
				+ "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ\n"
				+ "3201903010000012200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA\n"
				+ "2201903010000011200096206760173648****0099234234JOÃO MACEDO   BAR DO JOÃO       \n"
				+ "1201903010000015200096206760171234****7890233000JOÃO MACEDO   BAR DO JOÃO       \n"
				+ "2201903010000010700845152540738723****9987123333MARCOS PEREIRAMERCADO DA AVENIDA\n"
				+ "2201903010000050200845152540738473****1231231233MARCOS PEREIRAMERCADO DA AVENIDA\n"
				+ "3201903010000060200232702980566777****1313172712JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n"
				+ "1201903010000020000556418150631234****3324090002MARIA JOSEFINALOJA DO Ó - MATRIZ\n"
				+ "5201903010000080200845152540733123****7687145607MARCOS PEREIRAMERCADO DA AVENIDA\n"
				+ "2201903010000010200232702980568473****1231231233JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n"
				+ "3201903010000610200232702980566777****1313172712JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n"
				+ "4201903010000015232556418150631234****6678100000MARIA JOSEFINALOJA DO Ó - FILIAL\n"
				+ "8201903010000010203845152540732344****1222123222MARCOS PEREIRAMERCADO DA AVENIDA\n"
				+ "3201903010000010300232702980566777****1313172712JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n"
				+ "9201903010000010200556418150636228****9090000000MARIA JOSEFINALOJA DO Ó - MATRIZ\n"
				+ "4201906010000050617845152540731234****2231100000MARCOS PEREIRAMERCADO DA AVENIDA\n"
				+ "2201903010000010900232702980568723****9987123333JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n"
				+ "8201903010000000200845152540732344****1222123222MARCOS PEREIRAMERCADO DA AVENIDA\n"
				+ "2201903010000000500232702980567677****8778141808JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n"
				+ "3201903010000019200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA");
		
		MockMultipartFile arquivo = new MockMultipartFile(
			    "arquivo",
			    "cnab.txt",
			    MediaType.TEXT_PLAIN_VALUE,
			    sb.toString().getBytes()
			);
		List<OperacoesDTO> listOperacoes = new ArrayList<>();
		listOperacoes.add(OperacoesDTO.builder().build());
		
		when(service.upload(arquivo)).thenReturn(listOperacoes);
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.multipart(uri)				
				.file(arquivo))				
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		assertNotNull(result);
		
	}

}
