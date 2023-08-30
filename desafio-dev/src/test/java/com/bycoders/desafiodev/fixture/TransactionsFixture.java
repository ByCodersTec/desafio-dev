package com.bycoders.desafiodev.fixture;

import com.bycoders.desafiodev.domain.TransactionType;
import com.bycoders.desafiodev.domain.Transactions;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionsFixture {

    public static Transactions withRandomData() {
        Transactions transaction = new Transactions();

        TransactionType transactionType = TransactionTypeFixture.withRandomData();
         transaction.setTransactionType(transactionType);

        transaction.setInsertDate(LocalDate.now());
        transaction.setMovimentValue(100.0);
        transaction.setCpf(RandomStringUtils.randomNumeric(11));
        transaction.setCard("6777****1313");
        transaction.setInsertHour(LocalTime.now());
        transaction.setStoreName(RandomStringUtils.randomAlphabetic(10));
        transaction.setStorePropertyName(RandomStringUtils.randomAlphabetic(15));

        return transaction;
    }

}
