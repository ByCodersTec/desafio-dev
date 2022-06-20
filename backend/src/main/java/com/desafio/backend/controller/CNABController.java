package com.desafio.backend.controller;

import java.util.List;

import com.desafio.backend.model.CNAB;
import com.desafio.backend.repository.CNABRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/desafio/server")
public class CNABController {

    @Autowired
    private CNABRepository cnabRepository;

    // get all CNABs
    @GetMapping("/cnab")
    public List<CNAB> listCNAB() {
        return cnabRepository.findAll();
    }

    // create CNAB rest api
    @PostMapping("/cnab")
    public void updateCNAB(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
    }
}


