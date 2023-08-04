/*package br.com.confidencecambio.controller;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.confidencecambio.dto.TipoPessoa;
import br.com.confidencecambio.service.PessoaService;

@WebMvcTest({PessoaController.class, TransacoesService.class})
class PessoaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testClienteNomeComDoisSobrenome() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = "Andrelino Martins de Souza";
		String tipoPessoa = TipoPessoa.CLIENTE.toString();
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				)
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		assertEquals("{\"nomeCompleto\":\"ANDRELINO MARTINS DE SOUZA\",\"primeiroNome\":\"Andrelino\",\"nomeUltimo\":\"Andrelino Souza\",\"nomeAbreviado\":\"Andrelino M. Souza\"}", json);
	}
	@Test
	void testGerenteNomeComUmSobrenome() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = "Jose da Silva";
		String tipoPessoa = TipoPessoa.GERENTE.toString();
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				)
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		assertEquals("{\"nomeCompleto\":\"JOSE DA SILVA\",\"primeiroNome\":\"Jose\",\"nomeUltimo\":\"Jose Silva\",\"nomeAbreviado\":\"Jose Silva\"}", json);
	}
	@Test
	void testRoboNomeComUmNome() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = "Aspirador";
		String tipoPessoa = TipoPessoa.ROBO.toString();
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				)
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		assertEquals("{\"nomeCompleto\":\"ASPIRADOR\",\"primeiroNome\":\"Aspirador\",\"nomeUltimo\":\"Aspirador\",\"nomeAbreviado\":\"Aspirador\"}", json);
	}
	@Test
	void testRoboNomeComEspacoFinal() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = "Aspirador ";
		String tipoPessoa = TipoPessoa.ROBO.toString();
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400)
				)
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		assertEquals("[Não pode haver espaço em branco no inicio e no final do nome.]", json);
	}
	
	@Test
	void testRoboNomeVazio() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = "";
		String tipoPessoa = TipoPessoa.ROBO.toString();
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400)
				)
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		assertEquals("[O campo nome não pode ser vazio ou Branco]", json);
	}
	
	@Test
	void testGerenteNomeComUmDUSobrenome() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = "Maria du Reis";
		String tipoPessoa = TipoPessoa.GERENTE.toString();
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				)
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		assertEquals("{\"nomeCompleto\":\"MARIA DU REIS\",\"primeiroNome\":\"Maria\",\"nomeUltimo\":\"Maria Reis\",\"nomeAbreviado\":\"Maria Reis\"}", json);
	}
	
	@Test
	void testGerenteNomeComDOSSobrenome() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = "Maria dos Reis";
		String tipoPessoa = TipoPessoa.GERENTE.toString();
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				)
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		assertEquals("{\"nomeCompleto\":\"MARIA DOS REIS\",\"primeiroNome\":\"Maria\",\"nomeUltimo\":\"Maria Reis\",\"nomeAbreviado\":\"Maria Reis\"}", json);
	}
	
	@Test
	void testGerenteNomeComDISobrenome() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = "Maria di Paula";
		String tipoPessoa = TipoPessoa.GERENTE.toString();
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				)
		.andReturn();
		
		String json = result.getResponse().getContentAsString();
		assertEquals("{\"nomeCompleto\":\"MARIA DI PAULA\",\"primeiroNome\":\"Maria\",\"nomeUltimo\":\"Maria Paula\",\"nomeAbreviado\":\"Maria Paula\"}", json);
	}
	
	@Test
	void testGerenteNomeNulo() throws Exception {
		URI uri = new URI("/pessoa");
		String nome = null;
		String tipoPessoa = TipoPessoa.GERENTE.toString();
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.param("nome", nome)
				.param("tipoPessoa", tipoPessoa)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400)
				)
		.andReturn();
	}
}
*/
	