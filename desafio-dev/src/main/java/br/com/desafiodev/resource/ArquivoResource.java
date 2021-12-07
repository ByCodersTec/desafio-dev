package br.com.desafiodev.resource;

import br.com.desafiodev.dto.TransacaoDTO;
import br.com.desafiodev.model.Transacao;
import br.com.desafiodev.resource.openapi.ArquivoResourceOpenApi;
import br.com.desafiodev.service.ArquivoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController()
@RequestMapping("/api/arquivos")
@Slf4j
public class ArquivoResource implements ArquivoResourceOpenApi {


    private ArquivoService service;

    private ModelMapper modelMapper;

    public ArquivoResource(ArquivoService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = {"multipart/form-data", "application/json"}, produces = "application/json")
    public ResponseEntity<?> uploadArquivo(@RequestParam("cnab-file") MultipartFile file) throws IOException {
        log.info("Recebendo arquivo CNAB para nomalização.");
        service.salvaArquivo(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public Page<TransacaoDTO> findAll(@RequestParam(value = "nomeEmpresa", required = false) String nomeEmpresa,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "100") int size,
                                      TransacaoDTO dto, Pageable pageRequest) {
        Transacao filter = modelMapper.map(dto, Transacao.class);
        Page<Transacao> result = service.findAll(filter, pageRequest);
        List<TransacaoDTO> list = result.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, TransacaoDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<TransacaoDTO>(list, pageRequest, result.getTotalElements());
    }


}

