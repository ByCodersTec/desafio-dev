package com.github.filipednb.financialtransactions.file.impl;

import com.ancientprogramming.fixedformat4j.exception.FixedFormatException;
import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;
import com.github.filipednb.financialtransactions.file.FileParser;
import com.github.filipednb.financialtransactions.file.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileParserImpl implements FileParser {

    private static  final Logger log = LoggerFactory.getLogger(FileParserImpl.class);

    private static final FixedFormatManager manager = new FixedFormatManagerImpl();



    @Override
    public List<TransactionDTO> parse(MultipartFile file) {
        log.info("M=parse, I=Parsing file, file={}", file);
        try {

            String fileContent = new String(file.getBytes());
            List<String> fileLines = Arrays.asList(fileContent.split("[\\r\\n]+"));

            return fileLines.stream()
                    .map(line -> manager.load(TransactionDTO.class, line))
                    .collect(Collectors.toList());

        } catch (IOException | FixedFormatException ex) {
            log.error(ex.getMessage());
        }

        return null;
    }
}
