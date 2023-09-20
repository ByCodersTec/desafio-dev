package br.com.bycoders.cnab.core.usecase;

import br.com.bycoders.cnab.core.domain.Cnab;
import br.com.bycoders.cnab.core.gateway.CnabGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ManageReceiveFileUseCaseImpl implements ManageReceiveFileUseCase {

    private final CnabGateway cnabGateway;

    public ManageReceiveFileUseCaseImpl(CnabGateway cnabGateway) {
        this.cnabGateway = cnabGateway;
    }

    @Override
    public void create(List<String> cnabList) {
        for (String item: cnabList) {
            final var cnab = generateCnab(item);
            cnabGateway.save(cnab);
        }
    }

    private Cnab generateCnab(String item) {
        Cnab cnab = new Cnab();
        cnab.setType(item.charAt(0));
        cnab.setDate(item.substring(1,9).trim());
        cnab.setValue(calcValue(item));
        cnab.setCpf(item.substring(19,30).trim());
        cnab.setCard(item.substring(30,42).trim());
        cnab.setHour(item.substring(42,48).trim());
        cnab.setOwner(item.substring(48,62).trim());
        cnab.setName(item.substring(62).trim());
        return cnab;
    }

    private static BigDecimal calcValue(String item) {
        final var value = new BigDecimal(item.substring(9, 19).trim());
        return value.divide(new BigDecimal(100));
    }
}
