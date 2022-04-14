package com.bycoders.cnab.dominio.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.bycoders.cnab.dominio.entidades.Cnab;
import com.bycoders.cnab.dominio.repositories.CnabRepository;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@ApplicationScoped
public class CnabService {
    @Inject CnabRepository repository;
    
    @Transactional
    public StringBuilder uploadArquivo(final MultipartFormDataInput input){

        final StringBuilder content = new StringBuilder();
        try {

            final InputPart file = input.getFormDataMap().get("file").get(0);
            final InputStream inputStream = file.getBody(InputStream.class, null);
            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line;
            
            while ((line = br.readLine()) != null) {
                String tipoTransacao = line.substring(0,1);
                String dataOcorrencia =  line.substring(1,9);
                String valorMovimentacao = line.substring(9,19);
                String cpf = line.substring(19, 30);
                String cartao = line.substring(30, 42);
                String hora = line.substring(42, 48);
                String nomeRepresentante = line.substring(48, 62);
                String nomeLoja = line.substring(62);

                content.append(line);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return content;
    }

    public List<Cnab> obterTodosRegistros(){
        return repository.findAll().list();
    }
}
