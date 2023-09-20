package br.com.bycoders.cnab.core.usecase;

import br.com.bycoders.cnab.core.domain.Cnab;
import br.com.bycoders.cnab.core.gateway.CnabGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ManageCnabUseCaseImpl.class)
class ManageCnabUseCaseImplTest {

    @MockBean
    CnabGateway cnabGateway;

    @Autowired
    ManageCnabUseCaseImpl manageCnabUseCase;

    @Test
    void findAll_should_success() {
        when(cnabGateway.findAll()).thenReturn(cnabList());

        final var response = manageCnabUseCase.findAll();

        assertNotNull(response);
        assertEquals(3, response.size());
    }

    private List<Cnab> cnabList() {
        return Arrays.asList(
                new Cnab(),
                new Cnab(),
                new Cnab()
        );
    }

}