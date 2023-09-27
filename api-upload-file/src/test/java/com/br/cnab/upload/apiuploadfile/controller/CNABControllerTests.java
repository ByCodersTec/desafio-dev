package com.br.cnab.upload.apiuploadfile.controller;

import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;
import com.br.cnab.upload.apiuploadfile.service.CnabFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CnabFileController.class)
@AutoConfigureMockMvc
public class CNABControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CnabFileService cnabFileService;

    @Test
    public void testStoreOperationsEndpoint() throws Exception {
        // Criar algumas operações fictícias para o serviço
        List<StoreOperationResponse> storeOperations = Arrays.asList(
                new StoreOperationResponse("Store A", 100.0),
                new StoreOperationResponse("Store B", 200.0));

        // Simular o serviço de listar operações de loja
        when(cnabFileService.listStoreOperations()).thenReturn(storeOperations);

        // Executar a solicitação GET para o endpoint
        mockMvc.perform(get("/store-operations"))
                .andExpect(status().isOk())
                .andExpect(view().name("store-operations"))
                .andExpect(model().attributeExists("storeOperationsList"))
                .andExpect(model().attribute("storeOperationsList", storeOperations));
    }

    @Test
    public void testHandleFileUploadEndpoint() throws Exception {
        // Arquivo de exemplo para upload
        MockMultipartFile file = new MockMultipartFile("file", "example.cnab", "text/plain", "Example data".getBytes());

        // Simular o serviço de processamento do arquivo
        doNothing().when(cnabFileService).processCNABFile(file);

        // Executar a solicitação POST para o endpoint de upload
        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("File uploaded successfully."));

        // Verificar se o serviço foi chamado corretamente
        verify(cnabFileService, times(1)).processCNABFile(file);
    }
}

