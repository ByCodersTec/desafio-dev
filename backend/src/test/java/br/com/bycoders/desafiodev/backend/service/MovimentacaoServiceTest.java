package br.com.bycoders.desafiodev.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.desafiodev.backend.model.Movimentacao;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class MovimentacaoServiceTest {
    
    @Autowired
    private MovimentacaoService movimentacaoService;

    private MultipartFile multipartFile;

    private ClassLoader classLoader;

    @BeforeAll
    public void setup() throws IOException {
        
        classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("cnab-teste-exemplo-1.txt").getFile());
        InputStream stream =  new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", stream);
    }

    @Test
    public void deveRetornarUmaListaCom21Linhas() throws IOException{

        List<String> listaLinhas = movimentacaoService.obterLinhas(multipartFile);
        
        assertEquals(21, listaLinhas.size());
    }

    @Test
    public void deveRetornar21Movimentacoes() throws IOException{

        List<Movimentacao> lista = movimentacaoService.extrairDados(multipartFile);

        assertEquals(21, lista.size());
    }

}
