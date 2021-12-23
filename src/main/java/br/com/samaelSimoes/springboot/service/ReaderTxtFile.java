package br.com.samaelSimoes.springboot.service;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

@RestController
public class ReaderTxtFile {
    public String readFile(InputStream data) {
        try {
            BufferedReader readFile = new BufferedReader(new InputStreamReader(data));
            StringBuffer infs = new StringBuffer();
            while(readFile.ready()) {
                String line = readFile.readLine();
                infs.append(line).append(line).append("\r\n");
            }
            return infs.toString();
        } catch (IOException err) {
            return "error in reading the file";
        }
    }
}
