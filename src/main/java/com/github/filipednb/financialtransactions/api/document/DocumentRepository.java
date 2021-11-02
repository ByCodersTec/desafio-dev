package com.github.filipednb.financialtransactions.api.document;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

    DocumentEntity findByNumDocument(String numDocument);

}
