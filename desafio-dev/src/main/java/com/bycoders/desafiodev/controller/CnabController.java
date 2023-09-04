package com.bycoders.desafiodev.controller;


import com.bycoders.desafiodev.dto.StoreDTO;
import com.bycoders.desafiodev.service.CnabService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@RestController
@RequestMapping(value = "/cnab")
public class CnabController {

    private final CnabService service;

    public CnabController(CnabService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/saveCnab")
    @ResponseBody
    public ResponseEntity<String> saveCnab(@RequestParam("file") MultipartFile file) throws IOException {

        String fileContent = new String(file.getBytes(), "UTF-8");
        String resultMessage = service.saveCnab(fileContent);

        if (resultMessage.equals("Cnab file processed successfully.")) {
            return ResponseEntity.ok(resultMessage);
        } else {
            return ResponseEntity.badRequest().body(resultMessage);
        }
    }


    @GetMapping("/store")
    public ResponseEntity<StoreDTO> getStore(@RequestParam String name) throws NotFoundException {

        var result = service.findStoreName(name);

        return ResponseEntity.ok(result);
    }
}
