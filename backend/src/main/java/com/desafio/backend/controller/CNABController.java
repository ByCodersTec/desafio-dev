package com.desafio.backend.controller;

import java.io.IOException;
import java.util.List;

import com.desafio.backend.model.CNAB;
import com.desafio.backend.repository.CNABRepository;
import com.desafio.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/desafio/server")
public class CNABController {

    @Autowired
    private CNABRepository cnabRepository;

    @Autowired
    private FileService fileService;

    // get all CNABs
    @GetMapping("/cnab")
    public List<CNAB> listCNAB() {
        return cnabRepository.findAll();
    }

    // create CNAB rest api
    @PostMapping("/cnab")
    public void updateCNAB(@RequestParam("file") MultipartFile file) throws IOException {
        List<CNAB> cnabs = fileService.readFile(file);
        cnabRepository.saveAll(cnabs);
    }
}


