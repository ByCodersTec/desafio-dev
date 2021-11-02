package com.github.filipednb.financialtransactions.file.impl;

import com.github.filipednb.financialtransactions.file.FileParser;
import com.github.filipednb.financialtransactions.file.TransactionDTO;
import com.github.filipednb.financialtransactions.file.exception.FileParsingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class FileParserImplTest {

    @Autowired
    FileParser parser;

    @Test
    public void shouldParseFileAndConvertToDTO() throws IOException {
        MultipartFile file = getMultipartFile("files/valid_CNAB.txt");

        List<TransactionDTO> transactionDTOS = parser.parse(file);

        Assertions.assertEquals(21, transactionDTOS.size(), "Parsed file has not expected size" );
        Assertions.assertEquals("09620676017", transactionDTOS.get(0).getCpf(), "Parsed file item has not correct CPF value" );
    }

    @Test
    public void shouldThrowParseException() throws IOException {
        MultipartFile file = getMultipartFile("files/invalid_CNAB.txt");

        Assertions.assertThrows(FileParsingException.class, () -> {
            List<TransactionDTO> transactionDTOS = parser.parse(file);
        });


    }


    private MultipartFile getMultipartFile(String filePath) throws IOException {
        String fileContent;
        fileContent = new String(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResourceAsStream(filePath))
                .readAllBytes());
        return new MockMultipartFile(filePath, fileContent.getBytes());
    }


}