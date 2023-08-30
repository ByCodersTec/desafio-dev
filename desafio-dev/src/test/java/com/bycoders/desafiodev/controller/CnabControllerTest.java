package com.bycoders.desafiodev.controller;

import com.bycoders.desafiodev.dto.StoreDTO;
import com.bycoders.desafiodev.service.CnabService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CnabController.class)
class CnabControllerTest {


    public static final String CNAB = "/cnab/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CnabService service;

    @Test
    public void shouldValidateSaveCNABSuccesfull() throws Exception {
        String successMessage = "Cnab file processed successfully.";
        MockMultipartFile mockFile = new MockMultipartFile("file", "mock_cnab.txt", "text/plain", "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO ".getBytes());

        when(service.saveCnab(anyString())).thenReturn(successMessage);

        mockMvc.perform(MockMvcRequestBuilders.multipart(CNAB + "saveCnab")
                        .file(mockFile))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldValidateGetStoreNameSuccessfull() throws Exception {
        String name = "StoreName";

        StoreDTO mockStoreDTO = new StoreDTO();
        when(service.findStoreName(anyString())).thenReturn(mockStoreDTO);

        mockMvc.perform(get(CNAB + "store")
                        .param("name", name))
                .andExpect(status().isOk());
    }
}