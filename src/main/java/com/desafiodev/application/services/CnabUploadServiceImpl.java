package com.desafiodev.application.services;

import com.desafiodev.application.domains.Cnab;
import com.desafiodev.application.domains.Store;
import com.desafiodev.application.domains.Transaction;
import com.desafiodev.application.domains.exceptions.IllegalStateExceptionFactory;
import com.desafiodev.application.ports.in.UploadService;
import com.desafiodev.application.ports.out.StoreRepository;
import com.desafiodev.application.ports.out.TransactionRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CnabUploadServiceImpl implements UploadService {

  private final TransactionRepository transactionRepository;

  private final StoreRepository storeRepository;

  @Autowired
  public CnabUploadServiceImpl(
      TransactionRepository transactionRepository, StoreRepository storeRepository) {
    this.transactionRepository = transactionRepository;
    this.storeRepository = storeRepository;
  }

  @Override
  public void accept(@NonNull File uploadFile) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(uploadFile));
      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        Cnab cnab = Cnab.newInstance(line);
        Store store =
            storeRepository
                .findByNameAndOwnerName(cnab.getStoreName(), cnab.getOwner())
                .orElse(Store.from(cnab));
        Transaction transaction = Transaction.parse(Cnab.newInstance(line), store.getStoreId());
        Store newStore = store.sum(transaction);
        storeRepository.save(newStore);
        transactionRepository.save(transaction, newStore);
      }
      reader.close();
    } catch (IOException e) {
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("File not accepted")
          .param("exception", e)
          .param("file", uploadFile)
          .build();
    }
  }
}
