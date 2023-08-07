package br.com.bycoders.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.bycoders.dto.OperacoesDTO;

@Service
public class UploadService {
	@Autowired
	private RestTemplate restTemplate;

	public ModelAndView upload(MultipartFile cnab) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
		ContentDisposition contentDisposition = ContentDisposition.builder("form-data").name("cnab")
				.filename(cnab.getName()).build();
		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
		HttpEntity<byte[]> fileEntity = new HttpEntity<>(cnab.getBytes(), fileMap);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("cnab", fileEntity);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<List<OperacoesDTO>> response = restTemplate.exchange(
				"http://localhost:8001/transacoes/cnab/upload", HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<List<OperacoesDTO>>() {
				});

		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			List<OperacoesDTO> operacoes = response.getBody();
			System.out.println(operacoes.get(0).getTransacoes().get(0).getId());
			ModelAndView modelAndView = new ModelAndView("success", "operacoes", operacoes);
			modelAndView.addObject("globalMessage", "Arquivo importado com sucesso");
			return modelAndView;
		}

		ModelAndView mv = new ModelAndView("cnab");
		mv.addObject("globalMessage", "Falha interna, comunique o suporte.");
		return mv;
	}

}
