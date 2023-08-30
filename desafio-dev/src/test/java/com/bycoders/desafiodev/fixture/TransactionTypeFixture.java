package com.bycoders.desafiodev.fixture;

import com.bycoders.desafiodev.domain.TransactionType;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.OffsetDateTime;

public class TransactionTypeFixture {


    public static TransactionType withRandomData() {
        TransactionType transactionType = new TransactionType();

        transactionType.setDescription(RandomStringUtils.randomNumeric(11));
        transactionType.setTransaction_type("1");
        transactionType.setSignal("+");
        transactionType.setDt_insert(OffsetDateTime.now());

        return transactionType;
    }
}
