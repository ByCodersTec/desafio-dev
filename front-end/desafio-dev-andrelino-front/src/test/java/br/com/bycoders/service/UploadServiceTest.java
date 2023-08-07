package br.com.bycoders.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.bycoders.dto.OperacoesDTO;

@SpringBootTest
class UploadServiceTest {
	
	
	@Autowired
	private UploadService service;

	@Test
	void testUpload() {

		RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
		StringBuilder sb = new StringBuilder("1201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ");
		   sb.append("2201903010000011200096206760173648****0099234234JOÃO MACEDO   BAR DO JOÃO       ");
		   sb.append("1201903010000015200096206760171234****7890233000JOÃO MACEDO   BAR DO JOÃO       ");


		 MockMultipartFile mockMultipartFile = new MockMultipartFile(
			"arquivo",
			"cnab.txt",
			MediaType.TEXT_PLAIN_VALUE,
			sb.toString().getBytes()
			);
		
		@SuppressWarnings("unchecked")
		ResponseEntity<List<OperacoesDTO>> mockResponseEntity = Mockito.mock(ResponseEntity.class);
		 
		when(restTemplateMock.exchange(Mockito.anyString(), HttpMethod.POST, Mockito.any(HttpEntity.class), new ParameterizedTypeReference<List<OperacoesDTO>>() {
		})).thenReturn(mockResponseEntity);

		ModelAndView modelAndView = service.upload(mockMultipartFile);
		
		assertEquals("success", modelAndView.getView().toString());
		assertEquals(HttpStatus.OK, modelAndView.getStatus());
	}

}
