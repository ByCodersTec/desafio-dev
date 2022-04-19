import { Component, OnInit } from '@angular/core';
import { CnabConsultaDTO } from 'src/app/models/cnab-consulta-dto';
import { CnabDTO } from 'src/app/models/cnab-dto';
import { CnabResumoDTO } from 'src/app/models/cnab-resumo-dto';
import { CnabRetornoConsultaDTO } from 'src/app/models/cnab-retorno-consulta-dto';
import { CnabService } from 'src/app/services/cnab.service';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.scss']
})
export class ConsultaComponent implements OnInit {
  cnabs:CnabRetornoConsultaDTO = new CnabRetornoConsultaDTO();
  cnabsResumo:CnabDTO[] = [];
  valorTotal:number = 0;
  invalidEntries = 0;

  displayedColumns: string[] = ['data', 'descricao', 'valor', 'numeroCartao'];

  constructor(private cnabService: CnabService) { }

  ngOnInit(){
    this.cnabService.buscarTodosRegistosCNAB()
      .subscribe(retorno => {
        this.cnabs = retorno;
        // this.cnabs.consultas.forEach(detalhe => {
          
        //   this.cnabsResumo.push(detalhe.cnabsDetalhamento);
        // })
        
    }, (error) => {
      console.log(error)
    });

  }

  getCalculoValores(cnabs:CnabDTO[]){
    let valorTotal = 0;
    console.log(cnabs);
    cnabs.forEach(cnab => {
      if(cnab.tipo.sinal == '+'){
        valorTotal =+ cnab.valor;
      }else{
        valorTotal =- cnab.valor;
      }
    });
    console.log(valorTotal);
    return valorTotal;
  }
}