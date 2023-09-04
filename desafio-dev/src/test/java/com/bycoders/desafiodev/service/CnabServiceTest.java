package com.bycoders.desafiodev.service;

import com.bycoders.desafiodev.domain.Transactions;
import com.bycoders.desafiodev.exception.ResourceNotFoundException;
import com.bycoders.desafiodev.fixture.TransactionTypeFixture;
import com.bycoders.desafiodev.fixture.TransactionsFixture;
import com.bycoders.desafiodev.repository.TransactionTypeRepository;
import com.bycoders.desafiodev.repository.TransactionsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CnabServiceTest {

    public static final String SUCCESSFULLY = "Cnab file processed successfully.";
    public static final String TRANSACTIONS_FOUND = "No transactions found";
    private CnabService cnabService;

    @Mock
    private TransactionsRepository transactionsRepository;

    @Mock
    private TransactionTypeRepository transactionTypeRepository;

    public static final String FILE_PATH = "/cnabFile.txt";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cnabService = new CnabService(transactionsRepository, transactionTypeRepository);
    }


    @Test
    public void shouldValidateSaveAndFindCABANAFileSuccessful() {
        var transactions = TransactionsFixture.withRandomData();
        var transactionsType = TransactionTypeFixture.withRandomData();

        when(transactionsRepository.save(any(Transactions.class))).thenReturn(transactions);
        when(transactionTypeRepository.findById(any(Long.class))).thenReturn(Optional.of(transactionsType));

        var result = cnabService.saveCnab("3201903010000019200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA");

        Assertions.assertEquals(SUCCESSFULLY, result);
    }

    @Test
    public void shouldValidateReturnTransactionValueSuccessfully() {
        var transactions =  TransactionsFixture.withRandomData();

        List<Transactions> list = new ArrayList<>();

        list.add(transactions);

        when(transactionsRepository.findBystoreNameContains(any(String.class))).thenReturn((list));

        var result = cnabService.findStoreName(list.stream().findFirst().toString());

        Assertions.assertEquals(result.getSumValues(), transactions.getMovimentValue());
        Assertions.assertEquals(result.getStoreName(), transactions.getStoreName());
    }

    @Test
    public void shouldValidateReturnTransactionNotFound() {

        when(transactionsRepository.findBystoreNameContains(any(String.class))).thenReturn(Collections.emptyList());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            cnabService.findStoreName("teste");
        });
    }


}