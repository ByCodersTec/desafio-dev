package com.desafio.backend.service;

import com.desafio.backend.model.CNAB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    private CNAB parseLine(String line) throws ParseException {
        String dateFormat = "yyyyMMdd";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        String tipo = line.substring(0, 1);
        Date data = dateFormatter.parse(line.substring(1, 9));
        Integer valor = Integer.valueOf(line.substring(9, 19)) / 100;
        String cpf = line.substring(19, 30);
        String cartao = line.substring(30, 42);
        LocalTime hora = LocalTime.parse(line.substring(42, 48), DateTimeFormatter.ofPattern("HHmmss"));
        String dono = line.substring(48, 62);
        String nomeLoja = line.substring(62);

        CNAB cnab = new CNAB(tipo, data, valor, cpf, cartao, hora, dono, nomeLoja);
        return cnab;
    }

    public List<CNAB> readFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<CNAB> cnabs = new ArrayList();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .forEach(line -> {
                    try {
                        cnabs.add(parseLine(line));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });

        return cnabs;
    }
}
