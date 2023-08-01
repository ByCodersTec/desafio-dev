package com.desafiodev.application.services;

import com.desafiodev.application.domains.Cnab;
import com.desafiodev.application.domains.Transaction;
import com.desafiodev.application.ports.in.UploadService;
import com.desafiodev.application.ports.out.TransactionRepository;
import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CnabUploadServiceImpl implements UploadService {

  private final TransactionRepository transactionRepository;

  @Autowired
  public CnabUploadServiceImpl(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Override
  public void accept(File uploadFile) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(uploadFile));
      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        Transaction transaction = Transaction.parse(Cnab.getInstance(line));
        transactionRepository.save(transaction);
      }
      reader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
