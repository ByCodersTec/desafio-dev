package com.bycoders.desafiodev.service;

import com.bycoders.desafiodev.domain.Transactions;
import com.bycoders.desafiodev.repository.CnabRepository;
import com.bycoders.desafiodev.utils.CnabUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CnabService {

    @Autowired
    private CnabRepository cnabRepository;

    public String saveCnab(String filePath) {

        try (InputStream inputStream = Transactions.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                cnabRepository.save(CnabUtils.fillCnab(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
