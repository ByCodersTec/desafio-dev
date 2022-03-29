import { Component, OnInit, ViewChild } from '@angular/core';
import {
  GuiColumn,
  GuiColumnMenu,
  GuiSorting,
  GuiSummaries,
  GuiInfoPanel,
  GuiSearching,
  GuiDataType,
  GuiGridComponent,
  GuiGridApi
} from '@generic-ui/ngx-grid';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';

interface ICnab {
  type_transaction: string;
  create_at: string;
  transaction_value: string;
  cpf: string;
  credit_card: string;
  time_create: string;
  owner: string;
  store_name: string;
}

@Component({
  selector: 'app-table-grid-parser',
  templateUrl: './table-grid-parser.component.html',
  styleUrls: ['./table-grid-parser.component.css']
})
export class TableGridParserComponent implements OnInit {
  @ViewChild('grid', { static: true })
  gridComponent: GuiGridComponent;
  cnab: Array<ICnab>;
  private API_URL: string = environment.API_URL;
  countClient: number;
  saldoEntradas: number;
  saldoSaidas: number;

  searching: GuiSearching = {
    enabled: true,
    placeholder: 'Procura',
    highlighting: true
  };

  columns: Array<GuiColumn> = [
    {
      header: 'Tipo',
      field: 'type_transaction',
      type: 'string',
      width: 220
    },
    {
      header: 'Data',
      field: 'create_at',
      type: 'string',
      width: 100
    },
    {
      header: 'Valor',
      field: 'transaction_value',
      width: 120
    },
    {
      header: 'CPF',
      field: 'cpf',
      width: 150
    },
    {
      header: 'Cartão',
      field: 'credit_card',
      width: 150
    },
    {
      header: 'Hora',
      field: 'time_create',
      width: 150
    },
    {
      header: 'proprietario',
      field: 'owner',
      width: 150
    },
    {
      header: 'Loja',
      field: 'store_name',
      width: 180
    }
  ];

  columnMenu: GuiColumnMenu = {
    enabled: true,
    sort: true,
    columnsManager: false
  };

  sorting: GuiSorting = {
    enabled: true,
    multiSorting: true
  };

  summaries: GuiSummaries = {
    enabled: true
  };

  pageChanged = {
    EventEmitter: 4
  };

  infoPanel: GuiInfoPanel = {
    enabled: true,
    columnsManager: false,
    schemaManager: false
  };
  constructor(private readonly httpClient: HttpClient) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.ngOnInit();
    }, 1000 * 10);
    this.saldoEntradas = 0;
    this.saldoSaidas = 0;
    this.getList();
  }

  getList() {
    this.httpClient.get(`${this.API_URL}/list`).subscribe((item: ICnab[]) => {
      item.map((inab) => {
        inab.cpf = this.formatCpf(inab.cpf);
        inab.create_at = this.formatData(inab.create_at);
        inab.time_create = this.formatTime(inab.time_create);
        this.getTranslations(inab.type_transaction, inab.transaction_value);
        inab.type_transaction = inab.type_transaction.toUpperCase();
        inab.transaction_value = this.formatMoney(inab.transaction_value);
      });

      this.cnab = item;
      this.countClient = this.cnab.length;
      return;
    });
  }

  getTranslations(type_transaction: string, transaction_value: string) {
    if (type_transaction == 'aluguel' || type_transaction == 'boleto' || type_transaction == 'financiamento') {
      this.saldoSaidas = parseFloat(transaction_value) + this.saldoSaidas;
      return;
    }
    this.saldoEntradas = parseFloat(transaction_value) + this.saldoEntradas;
    return;
  }

  formatTime(time_format: string) {
    moment.locale('pt-br');
    return moment(time_format, 'hmmss').format('HH:mm:ss');
  }

  formatCpf(cpf_format?: string) {
    let cpf = cpf_format.replace(/[^\d]/g, '');
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }

  formatData(date_format: string) {
    return moment(date_format).format('DD/MM/YYYY');
  }

  formatMoney(transaction) {
    let format_cnab_money = parseInt(transaction);
    return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(format_cnab_money);
  }
}
