package br.com.bycoders.cnab.infra.dataprovider;

import br.com.bycoders.cnab.core.domain.Cnab;
import br.com.bycoders.cnab.infra.entity.CnabEntity;
import br.com.bycoders.cnab.infra.repository.CnabRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CnabDataProvider.class)
class CnabDataProviderTest {

    @Autowired
    CnabDataProvider cnabDataProvider;

    @MockBean
    CnabRepository cnabRepository;

    @Test
    void save_should_success() {
        when(cnabRepository.save(any())).thenReturn(new CnabEntity());
        assertDoesNotThrow(() -> cnabDataProvider.save(new Cnab()));
        verify(cnabRepository, times(1)).save(any());
    }

    @Test
    void findAll_should_success() {
        when(cnabRepository.findAll()).thenReturn(cnabList());
        assertDoesNotThrow(() -> cnabDataProvider.findAll());
        verify(cnabRepository, times(1)).findAll();
    }

    private List<CnabEntity> cnabList() {
        return Arrays.asList(
                new CnabEntity(),
                new CnabEntity(),
                new CnabEntity()
        );
    }
}