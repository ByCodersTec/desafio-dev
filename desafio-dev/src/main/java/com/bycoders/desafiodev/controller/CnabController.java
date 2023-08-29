package com.bycoders.desafiodev.controller;


import com.bycoders.desafiodev.domain.Transactions;
import com.bycoders.desafiodev.service.CnabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
@RestController
@RequestMapping(value = "/cnab")
public class CnabController {

    @Autowired
    private CnabService service;

    @GetMapping
    public String saveCnab() throws IOException {

        String filePath = "/cnabFile.txt"; // Caminho relativo à pasta "resources"

        service.saveCnab(filePath);

        return "";
    }
}
