package br.com.samaelSimoes.springboot.control;

import br.com.samaelSimoes.springboot.service.ReaderTxtFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UpdateFilesCRTL {
    @RequestMapping(method = RequestMethod.POST, path = "/fileUpload")
    public String fileUpload(@RequestParam MultipartFile file) {
        try {
              ReaderTxtFile fileImport = new ReaderTxtFile();
            return fileImport.readFile(file.getInputStream());
        } catch (IOException err) {
            return  "error in reading the file";
        }
    }

    @GetMapping
    public String ola() {
        return "Ola";
    }
}
