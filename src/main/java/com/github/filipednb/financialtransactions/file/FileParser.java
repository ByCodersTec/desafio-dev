package com.github.filipednb.financialtransactions.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileParser {

    List<TransactionDTO> parse(MultipartFile file);

}
