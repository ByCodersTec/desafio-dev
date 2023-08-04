package br.com.bycoders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.dto.OperacoesDTO;
import br.com.bycoders.service.TransacoesService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {
	@Autowired
	private TransacoesService service;
	
	@Operation(summary = "Upload de arquivo CNAB e input dos dados")	 
	@PostMapping("/cnab/upload")	
	public List<OperacoesDTO> upload(@RequestParam("cnab") MultipartFile file) {		
		return service.upload(file);		
	}
}
