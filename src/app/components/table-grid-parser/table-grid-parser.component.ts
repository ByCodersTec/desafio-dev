import { Component, OnInit } from '@angular/core';
import {
  GuiColumn,
  GuiColumnMenu,
  GuiSorting,
  GuiSummaries,
  GuiInfoPanel,
} from '@generic-ui/ngx-grid';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-table-grid-parser',
  templateUrl: './table-grid-parser.component.html',
  styleUrls: ['./table-grid-parser.component.css'],
})
export class TableGridParserComponent implements OnInit {
  characters = [];

  columns: Array<GuiColumn> = [
    {
      header: 'Name',
      field: 'name',
      type: 'string',
    },
    {
      header: 'Image',
      field: 'image',
      sorting: false,
      view: (value: any) => {
        return `<span class="character">
						<img class="character__image" src="${value}" alt="">
					</span>`;
      },
      width: 100,
    },
    {
      header: 'Status',
      field: 'status',
      type: 'string',
      width: 100,
    },
    {
      header: 'Species',
      field: 'species',
      width: 100,
    },
    {
      header: 'Ep.',
      field: (item: any) => item.episode.length,
      type: 'number',
      width: 100,
      summaries: {
        enabled: true,
        summariesTypes: ['average', 'median'],
      },
    },
    {
      header: 'Gender',
      field: 'gender',
      width: 100,
    },
    {
      header: 'Origin',
      field: (item: any) => item.origin.name,
    },
  ];

  columnMenu: GuiColumnMenu = {
    enabled: true,
    sort: true,
    columnsManager: false,
  };

  sorting: GuiSorting = {
    enabled: true,
    multiSorting: true,
  };

  summaries: GuiSummaries = {
    enabled: true,
  };

  infoPanel: GuiInfoPanel = {
    enabled: true,
    columnsManager: false,
    schemaManager: false,
  };
  constructor(private readonly httpClient: HttpClient) {}

  ngOnInit(): void {
    this.httpClient
      .get('https://rickandmortyapi.com/api/character')
      .subscribe((data: any) => {
        this.characters = data.results;
      });
  }
}
