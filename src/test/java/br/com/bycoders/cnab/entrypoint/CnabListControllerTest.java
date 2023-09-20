package br.com.bycoders.cnab.entrypoint;

import br.com.bycoders.cnab.core.domain.Cnab;
import br.com.bycoders.cnab.core.usecase.ManageCnabUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CnabListController.class)
class CnabListControllerTest {

    @MockBean
    ManageCnabUseCase manageCnabUseCase;

    @Autowired
    CnabListController cnabListController;

    @Test
    void list_should_success() {
        when(manageCnabUseCase.findAll()).thenReturn(cnabList());

        final var response = cnabListController.cnabList();

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(3, response.getBody().size());
    }

    private List<Cnab> cnabList() {
        return Arrays.asList(
                new Cnab(),
                new Cnab(),
                new Cnab()
        );
    }

}