package br.com.samaelSimoes.springboot.services;

import br.com.samaelSimoes.springboot.entities.File;
import br.com.samaelSimoes.springboot.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    private List<Integer> appetizer = Arrays.asList(new Integer[] {1, 4, 5, 6, 7, 8});

    public void readFile(final InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while( reader.ready() ) {
            fileRepository.save(this.getDataFile(reader.readLine()));
        }
    }

    public List<Map> getAll() {
        Iterable<File> files = fileRepository.findAll(Sort.by("nmStore"));
        return this.transationsGroup(files);
    }

    private List<Map> transationsGroup(Iterable<File> files) {
        List<Map> lojas = new ArrayList<>();
        List<Map> transactions = new ArrayList<>();
        Map<String, Object> transaction = new HashMap<>();
        String nmStore = "";
        Map<String, Object> loja = new HashMap<>();
        Float balance = new Float(0);
        boolean hasLoja = false;

        for ( File file : files ) {
            hasLoja = true;

            if ( !nmStore.equalsIgnoreCase(file.getNmStore()) ) {
                if ( !nmStore.isEmpty() ) {
                    loja.put("account_balance", balance);
                    loja.put("transactions", transactions);
                    lojas.add(loja);
                    transactions = new ArrayList<>();
                }

                nmStore = file.getNmStore();
                loja = new HashMap<>();
                loja.put("store_name", nmStore);
                loja.put("cpf", file.getCpf());
                loja.put("owner_name", file.getNmOwner());
                balance = new Float(0);
            }

            transaction = new HashMap<>();
            transaction.put("type", this.getTransaction(file.getType()));
            transaction.put("operation", this.getTransaction(file.getType()));

            if ( appetizer.contains(file.getType()) ) {
                transaction.put("value", file.getValue());
                transaction.put("date", file.getDtTransaction());
                transactions.add(transaction);
                balance += file.getValue();
            } else {
                transaction.put("value", file.getValue());
                transaction.put("date", file.getDtTransaction());
                transactions.add(transaction);
                balance -= file.getValue();
            }
        }

        if ( hasLoja ) {
            loja.put("account_balance", balance);
            loja.put("transactions", transactions);
            lojas.add(loja);
        }

        return lojas;
    }

    private String getTransaction(final Integer type) {
        String description;
        switch ( type ) {
            case 1 : description = "debt";
                break;
            case 2 : description = "payment slip";
                break;
            case 3 : description = "Financing";
                break;
            case 4 : description = "Credit";
                break;
            case 5 : description = "Loan Receipt";
                break;
            case 6 : description = "Sales";
                break;
            case 7 : description = "TED receipt";
                break;
            case 8 : description = "DOC receipt";
                break;
            default: description = "Rent";
        }
        return description;
    }

    private File getDataFile(final String line) {
        String card = line.substring(30, 42);
        String hour = line.substring(42,48);
        String owner = line.substring(48, 62);
        String store = line.substring(62, line.length()).trim();
        Integer type = Integer.parseInt(line.substring(0 ,1));
        String date = line.substring(1,9);
        Float value = Float.parseFloat(line.substring(9,19)) / new Float(100.00);
        String cpf = line.substring(19, 30);

        LocalDateTime datetime = LocalDateTime.now()
                .withYear(Integer.parseInt(date.substring(0, 4)))
                .withMonth(Integer.parseInt(date.substring(5, 6)))
                .withDayOfMonth(Integer.parseInt(date.substring(7, 8)))
                .withHour(Integer.parseInt(hour.substring(1 ,2)))
                .withMinute(Integer.parseInt(hour.substring(3, 4)))
                .withSecond(Integer.parseInt(hour.substring(5, 6)));

        return new File(type, datetime, value, cpf, card, owner, store);
    }

    public List<Map> getAllStore(final String Store) {
        Iterable<File> files = fileRepository.findByNmStoreContainingIgnoreCase(Store);
        return this.transationsGroup(files);
    }
}
