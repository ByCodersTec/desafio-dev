package br.com.bycoders.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.bycoders.dto.OperacoesDTO;

@SpringBootTest
class UploadServiceTest {
	@MockBean
	private RestTemplate restTemplate;	
	
	@Autowired
	private UploadService service;

	@Test
	void testUpload() throws IOException {

		restTemplate = Mockito.mock(RestTemplate.class);
		StringBuilder sb = new StringBuilder(
				"1201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ");
		sb.append("2201903010000011200096206760173648****0099234234JOÃO MACEDO   BAR DO JOÃO       ");
		sb.append("1201903010000015200096206760171234****7890233000JOÃO MACEDO   BAR DO JOÃO       ");

		MockMultipartFile mockMultipartFile = new MockMultipartFile("arquivo", "cnab.txt", MediaType.TEXT_PLAIN_VALUE,
				sb.toString().getBytes());
		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
		ContentDisposition contentDisposition = ContentDisposition.builder("form-data").name("cnab")
				.filename(mockMultipartFile.getName()).build();
		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

		@SuppressWarnings("unchecked")
		ResponseEntity<List<OperacoesDTO>> mockResponseEntity = Mockito.mock(ResponseEntity.class);

		when(restTemplate.exchange("http://localhost:8001/transacoes/cnab/upload", HttpMethod.POST,
				Mockito.mock(HttpEntity.class), Mockito.mock(ParameterizedTypeReference.class)))
				.thenReturn(mockResponseEntity);

		ModelAndView modelAndView = service.upload(mockMultipartFile);
		assertNotNull(modelAndView);
	}

}
