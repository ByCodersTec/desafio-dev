package com.bycoders.desafiodev.service;

import com.bycoders.desafiodev.domain.Transactions;
import com.bycoders.desafiodev.dto.StoreDTO;
import com.bycoders.desafiodev.exception.NotFoundException;
import com.bycoders.desafiodev.mapper.StoreMapper;
import com.bycoders.desafiodev.repository.TransactionTypeRepository;
import com.bycoders.desafiodev.repository.TransactionsRepository;
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
    private TransactionsRepository transactionsRepository;


    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public String saveCnab(String filePath) {
        try (InputStream inputStream = Transactions.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                var transactions = CnabUtils.fillCnab(line);

                var result = transactionTypeRepository.findById(Long.valueOf(line.substring(0, 1)));

                if (result.isEmpty())
                    throw new NotFoundException("Invalid Transaction Type");

                transactions.setTransactionType(result.get());

                transactionsRepository.save(transactions);
            }
            return "Cnab file processed successfully.";
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
            return "Error processing Cnab file.";
        }
    }

    public StoreDTO findStoreName(String name) throws NotFoundException {

        var storeTransactions = transactionsRepository.findBystoreNameContains(name);

        if (storeTransactions.isEmpty()) {
            throw new NotFoundException("No transactions found");
        }

        double totalValue = 0.0;

        for (Transactions transaction : storeTransactions) {
            if (transaction.getTransactionType().getSignal().equals("+")) {
                totalValue += transaction.getMovimentValue();
            } else if (transaction.getTransactionType().getSignal().equals("-")) {
                totalValue -= transaction.getMovimentValue();
            }
        }
        return StoreMapper.toResponse(name, totalValue);
    }
}
