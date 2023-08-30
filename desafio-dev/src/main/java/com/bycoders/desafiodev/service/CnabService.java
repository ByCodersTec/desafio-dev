package com.bycoders.desafiodev.service;

import com.bycoders.desafiodev.domain.Transactions;
import com.bycoders.desafiodev.dto.StoreDTO;
import com.bycoders.desafiodev.exception.NotFoundException;
import com.bycoders.desafiodev.mapper.StoreMapper;
import com.bycoders.desafiodev.repository.TransactionTypeRepository;
import com.bycoders.desafiodev.repository.TransactionsRepository;
import com.bycoders.desafiodev.utils.CnabUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CnabService {

    @Autowired
    private TransactionsRepository transactionsRepository;


    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public String saveCnab(String fileContent) {
        String[] lines = fileContent.split("\r\n");

        for (String line : lines) {
            var transactions = CnabUtils.fillCnab(line);

            var result = transactionTypeRepository.findById(Long.valueOf(line.substring(0, 1)));

            if (result.isEmpty())
                throw new NotFoundException("Invalid Transaction Type");

            transactions.setTransactionType(result.get());

            transactionsRepository.save(transactions);
        }

        return "Cnab file processed successfully.";
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
