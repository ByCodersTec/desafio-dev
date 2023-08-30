package com.bycoders.desafiodev.controller;


import com.bycoders.desafiodev.dto.StoreDTO;
import com.bycoders.desafiodev.service.CnabService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@RestController
@RequestMapping(value = "/cnab")
public class CnabController {

    private final CnabService service;

    public CnabController(CnabService service) {
        this.service = service;
    }

    @GetMapping("/saveCnab")
    public ResponseEntity<String> saveCnab() {
        String filePath = "/cnabFile.txt";

        String resultMessage = service.saveCnab(filePath);

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
