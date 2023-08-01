package com.desafiodev.application.ports.out;

import com.desafiodev.application.domains.Transaction;

public interface TransactionRepository {

  void save(Transaction transaction);
}
