package com.br.cnab.upload.apiuploadfile.controller;

import com.br.cnab.upload.apiuploadfile.builder.StoreOperationResponseBuilder;
import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;
import com.br.cnab.upload.apiuploadfile.service.TransactionFileService;
import com.br.cnab.upload.apiuploadfile.service.impl.TransactionFileServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.br.cnab.upload.apiuploadfile.builder.StoreOperationResponseBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionFileController.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class TransactionControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionFileServiceImpl transactionFileService;


    private static String writeValueAsString(Object value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(value);
    }

    @BeforeEach
    public void setUp() {
        mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext)
                        .addFilter(
                                (request, response, chain) -> {
                                    response.setCharacterEncoding("UTF-8");
                                    chain.doFilter(request, response);
                                },
                                "/*")
                        .build();
    }

    @Test
    public void testHandleFileUploadWithValidFile() throws Exception {

        Path filePath = ResourceUtils.getFile("classpath:CNAB.txt").toPath();
        byte[] fileContent = Files.readAllBytes(filePath);

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "CNAB.txt",
                "text/plain",
                fileContent
        );

        doNothing().when(transactionFileService).processTransactionFile(file);

        mockMvc.perform(multipart("/api/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("File uploaded successfully."));

        verify(transactionFileService).processTransactionFile(file);
    }

    @Test
    void testStoreOperations() throws Exception {
        List<StoreOperationResponse> storeOperationList = storeOperationResponseBuilder().buildList();

        when(this.transactionFileService.listStoreOperations()).thenReturn(storeOperationList);

        var expectedResponse = writeValueAsString(storeOperationList).toCharArray();

        var response =
                this.mockMvc
                        .perform(
                                get("/api/store-operations")
                                        .accept(MediaType.APPLICATION_JSON)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
                        .toCharArray();

        Arrays.sort(response);
        Arrays.sort(expectedResponse);

        assertThat(new String(response)).isNotNull().isEqualTo(new String(expectedResponse));
    }
}

