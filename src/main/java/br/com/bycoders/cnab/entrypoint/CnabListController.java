package br.com.bycoders.cnab.entrypoint;

import br.com.bycoders.cnab.core.domain.Cnab;
import br.com.bycoders.cnab.core.usecase.ManageCnabUseCase;
import br.com.bycoders.cnab.core.usecase.ManageReceiveFileUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cnab")
public class CnabListController {
    private final ManageCnabUseCase manageCnabUseCase;

    public CnabListController(ManageReceiveFileUseCase manageReceiveFileUseCase) {
        this.manageCnabUseCase = manageCnabUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Cnab>> cnabList() {
        return ResponseEntity.ok().body(manageCnabUseCase.findAll());
    }


}
