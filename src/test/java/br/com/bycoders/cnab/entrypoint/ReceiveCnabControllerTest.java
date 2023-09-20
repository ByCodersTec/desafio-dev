package br.com.bycoders.cnab.entrypoint;

import br.com.bycoders.cnab.core.usecase.ManageReceiveFileUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ReceiveCnabController.class)
class ReceiveCnabControllerTest {

    @Autowired
    ReceiveCnabController receiveCnabController;

    @MockBean
    ManageReceiveFileUseCase manageReceiveFileUseCase;

    @Test
    void cnabFile_should_success() throws IOException {
        File file = new File("src/test/resources/CNAB.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", input);

        doNothing().when(manageReceiveFileUseCase).create(anyList());
        final var response = receiveCnabController.receiveFile(multipartFile);

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(manageReceiveFileUseCase, times(1)).create(anyList());
    }
}