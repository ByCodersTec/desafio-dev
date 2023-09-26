package com.br.cnab.upload.apiuploadfile.repository;

import com.br.cnab.upload.apiuploadfile.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionFileRepository extends JpaRepository<Transaction, Long> {
}
