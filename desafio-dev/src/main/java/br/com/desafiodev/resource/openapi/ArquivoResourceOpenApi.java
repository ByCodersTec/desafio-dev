package br.com.desafiodev.resource.openapi;

import br.com.desafiodev.dto.TransacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Tag(name = "EndPoint - Arquivo CNAB", description = "Gerencia o envio de arqiuivos CNAB para a plataforma")
public interface ArquivoResourceOpenApi {

    @Operation(description = "Recebe e faz a leitura do arquivo CNAB para nomralização e gravação em banco de dados")
    @ApiResponse(responseCode = "404", description = "Arquivo não pode estar vazio")
    public ResponseEntity<?> uploadArquivo(@Parameter(name = "cnab-file", description = "arquivo cnba txt") MultipartFile file) throws IOException;

    @Parameters({
                @Parameter(name = "nomeEmpresa", description = "nomeEmpresa"),
                @Parameter(name = "page", description = "page = 0"),
                @Parameter(name = "size", description = "size = 100")
    })
    @Operation(description = "Lista transações por empresa informando o nome da empresa")
    public Page<TransacaoDTO> findAll(@Parameter(name = "nomeEmpresa", example = "BAR DO JOÃO") String nomeEmpresa,
                                      @Parameter(name = "page", example = "0") int page,
                                      @Parameter(name = "size", example = "100") int size,
                                      TransacaoDTO dto, Pageable pageRequest);
}
