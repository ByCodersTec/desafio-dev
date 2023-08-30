package com.bycoders.desafiodev.controller;

import com.bycoders.desafiodev.dto.StoreDTO;
import com.bycoders.desafiodev.service.CnabService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CnabController.class)
class CnabControllerTest {

    public static final String SUCCESSFULLY = "Cnab file processed successfully.";
    public static final String FILE_PATH = "/cnabFile.txt";
    public static final String CNAB = "/cnab/";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CnabService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldValidateSaveCNABSuccesfull() throws Exception {

        String filePath = FILE_PATH;
        when(service.saveCnab(filePath)).thenReturn(SUCCESSFULLY);

        mockMvc.perform(get(CNAB + "saveCnab"))
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