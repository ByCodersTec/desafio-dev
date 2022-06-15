package com.desafio.backend.controller;

import java.util.List;

import com.desafio.backend.model.CNAB;
import com.desafio.backend.repository.CNABRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CNABController {

    @Autowired
    private CNABRepository cnabRepository;

    // get all CNABs
    @GetMapping("/cnabs")
    public List<CNAB> getAllEmployees() {
        return cnabRepository.findAll();
    }

    // create CNAB rest api
    @PostMapping("/cnabs")
    public CNAB createEmployee(@RequestBody CNAB cnab) {
        return cnabRepository.save(cnab);
    }
}


