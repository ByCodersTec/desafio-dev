import { HttpClient } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { CnabService } from 'src/app/services/cnab.service';
import { environment } from 'src/environments/environment';
import { CnabDTO } from '../../../models/cnab-dto';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConsultaComponent implements OnInit {
  cnabs:CnabDTO[] = [];
  constructor(private cnabService: CnabService, public http: HttpClient) { }

  ngOnInit(){
    // this.http.get<CnabDTO[]>(`${environment.baseURL}/cnab`)
    this.cnabService.buscarTodosRegistosCNAB()
      .subscribe(retorno => {
        console.log("Chegou aqui");
        this.cnabs = retorno;
        console.log(retorno);
    }, (error) => {
      console.log("error")
      console.log(error)
    });

  }

}
