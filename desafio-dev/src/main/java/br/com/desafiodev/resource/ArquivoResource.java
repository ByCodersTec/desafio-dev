package br.com.desafiodev.resource;

import br.com.desafiodev.service.ArquivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequestMapping("/api/arquivos")
public class ArquivoResource {


    private ArquivoService service;

    public ArquivoResource(ArquivoService service) {
        this.service = service;
    }

    @PostMapping(consumes = {"multipart/form-data", "application/json"}, produces = "application/json")
    public ResponseEntity<?> uploadArquivo(@RequestParam("cnab-file") MultipartFile file) throws IOException {
        service.salvaArquivo(file);
        return ResponseEntity.ok().build();
    }


}

