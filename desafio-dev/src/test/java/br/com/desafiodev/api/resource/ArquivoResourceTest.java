package br.com.desafiodev.api.resource;

import br.com.desafiodev.service.ArquivoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class ArquivoResourceTest {

    static String CNAB_API = "/api/arquivos";
    static String ARQUIVO = "CNAB.txt";

    @Autowired
    MockMvc mvc;

    @MockBean
    ArquivoService service;

    @Test
    @DisplayName("Deve receber o upload do arquivo cnab")
    public void uploadArquivoCnabTest() throws Exception{

        MockMultipartFile arquivoCnab = new MockMultipartFile(
           "cnab-file",
           ARQUIVO,
           "text/plan",
           "conteudo do arquivo".getBytes()
        );

        MockMultipartHttpServletRequestBuilder multiparRequest =
                MockMvcRequestBuilders.multipart(CNAB_API);


        mvc.perform(multiparRequest.file(arquivoCnab))
                .andExpect(status().isOk());
    }




}
