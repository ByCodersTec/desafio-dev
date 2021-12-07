package br.com.desafiodev.api.resource;

import br.com.desafiodev.dto.TipoTransacaoDTO;
import br.com.desafiodev.dto.TransacaoDTO;
import br.com.desafiodev.model.TipoTransacao;
import br.com.desafiodev.model.Transacao;
import br.com.desafiodev.service.ArquivoService;
import br.com.desafiodev.utils.DataUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class ArquivoResourceTest {

    static String CNAB_API = "/api/arquivos";
    static String ARQUIVO = "CNAB.txt";

    @Autowired
    MockMvc mvc;

    @MockBean
    ArquivoService service;

    @Test
    @DisplayName("Deve receber o upload do arquivo cnab")
    public void uploadArquivoCnabTest() throws Exception{

        MockMultipartFile arquivoCnab = new MockMultipartFile(
           "cnab-file",
           ARQUIVO,
           "text/plan",
           "conteudo do arquivo".getBytes()
        );

        MockMultipartHttpServletRequestBuilder multiparRequest =
                MockMvcRequestBuilders.multipart(CNAB_API);


        mvc.perform(multiparRequest.file(arquivoCnab))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Deve filtar transações")
    public void findTransacaoTest() throws Exception {

        List<TransacaoDTO> lista = createListTransacao();

        Transacao transacao = Transacao.builder()
                .id(createNewTrasancao().getIdTransaco())
                .tipo(TipoTransacao.builder().id(1L).build())
                .dataTransacao(createNewTrasancao().getDataTransacao())
                .valor(createNewTrasancao().getValor())
                .cpf(createNewTrasancao().getCpf())
                .cartao(createNewTrasancao().getCartao())
                .hora(createNewTrasancao().getHora())
                .nomeDono(createNewTrasancao().getNomeDono())
                .nomeLoja(createNewTrasancao().getNomeLoja())
                .build();

        BDDMockito.given(service.findAll(Mockito.any(Transacao.class), Mockito.any(Pageable.class)))
                .willReturn(new PageImpl<Transacao>(Arrays.asList(transacao), PageRequest.of(0, 100), 1));

        String queryString = String.format("?nomeEmpresa=%s&page=0&size=100", transacao.getNomeLoja());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(CNAB_API.concat(queryString))
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("content", Matchers.hasSize(1)))
                .andExpect(jsonPath("totalElements").value(1))
                .andExpect(jsonPath("pageable.pageSize").value(100))
                .andExpect(jsonPath("pageable.pageNumber").value(0))
        ;
    }


    private List<TransacaoDTO> createListTransacao() throws IOException {

        List<TransacaoDTO> lista = new ArrayList<>();

        Files.lines(Paths.get(ARQUIVO))
                .forEach(cnab ->
                        lista.add(TransacaoDTO.builder()
                        .idTipo(TipoTransacaoDTO.builder().idTipo(Long.parseLong(cnab.substring(0, 1))).build())
                        .dataTransacao(DataUtils.parseLocalDate(cnab.substring(1,9)))
                        .valor(new BigDecimal(cnab.substring(9,19)).divide(BigDecimal.valueOf(100)))
                        .cpf(cnab.substring(19, 30))
                        .cartao(cnab.substring(30, 42))
                        .hora(DataUtils.parseLocalTime(cnab.substring(42, 48)))
                        .nomeDono(cnab.substring(48, 62))
                        .nomeLoja(cnab.substring(62, Math.min(cnab.length(), 81))).build())
                );
        return lista;
    }

    private TransacaoDTO createNewTrasancao() {
        return TransacaoDTO.builder().idTipo(new TipoTransacaoDTO().builder().idTipo(3L).build())
                .dataTransacao(LocalDate.parse("2019-03-01"))
                .valor(new BigDecimal("142.00"))
                .cpf("09620676017")
                .cartao("4753****3153")
                .hora(LocalTime.parse("15:34:53"))
                .nomeDono("JOÃO MACEDO")
                .nomeLoja("BAR DO JOÃO").build();
    }

}
