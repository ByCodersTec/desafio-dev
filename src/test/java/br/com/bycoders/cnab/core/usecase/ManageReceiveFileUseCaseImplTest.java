package br.com.bycoders.cnab.core.usecase;

import br.com.bycoders.cnab.core.gateway.CnabGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ManageReceiveFileUseCaseImpl.class)
class ManageReceiveFileUseCaseImplTest {

    @Autowired
    ManageReceiveFileUseCaseImpl manageReceiveFileUseCase;

    @MockBean
    CnabGateway cnabGateway;

    @Test
    void create_should_success() {
        doNothing().when(cnabGateway).save(any());
        manageReceiveFileUseCase.create(Arrays.asList("3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO"));

        verify(cnabGateway, times(1)).save(any());
    }
}