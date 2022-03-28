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

@Component({
  selector: 'app-table-grid-parser',
  templateUrl: './table-grid-parser.component.html',
  styleUrls: ['./table-grid-parser.component.css']
})
export class TableGridParserComponent implements OnInit {
  @ViewChild('grid', { static: true })
  gridComponent: GuiGridComponent;
  cnab = [];
  private API_URL: string = environment.API_URL;

  // id, type_transaction, create_at, transaction_value, cpf, credit_card, time_create, owner, store_name
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
      width: 180
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
      width: 100
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
      width: 100
    },
    {
      header: 'Dono da loja',
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

    this.getList();
  }

  getList() {
    this.httpClient.get(`${this.API_URL}/list`).subscribe((data: any) => {
      console.log(data);
      this.cnab = data;
    });
  }
}
