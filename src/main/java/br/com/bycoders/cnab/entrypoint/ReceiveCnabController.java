package br.com.bycoders.cnab.entrypoint;

import br.com.bycoders.cnab.core.usecase.ManageReceiveFileUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@RestController
@RequestMapping("/cnab")
public class ReceiveCnabController {
    private final ManageReceiveFileUseCase manageReceiveFileUseCase;

    public ReceiveCnabController(ManageReceiveFileUseCase manageReceiveFileUseCase) {
        this.manageReceiveFileUseCase = manageReceiveFileUseCase;
    }

    @PostMapping ("/file")
    public ResponseEntity<Void> receiveFile(@RequestParam("file") MultipartFile file) throws IOException {
        final var inputStream = new InputStreamReader(file.getInputStream(), "UTF-8");
        StringBuffer content = new StringBuffer();
        int c = 0;
        while ((c = inputStream.read()) != -1) {
            content.append((char) c);
        }
        manageReceiveFileUseCase.create(Arrays.asList(content.toString().split("\\n")));

        return ResponseEntity.ok().build();
    }


}
