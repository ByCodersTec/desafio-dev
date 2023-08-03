import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import * as signalR from '@microsoft/signalr';
import {MatPaginator, MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { TransactionsService } from './core/services/transactions.service';
import { Transaction } from './core/model/transaction';
import { SpinnerService } from './core/services/spinner.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterViewInit {
  title = 'frontend';
  showOverlay = true;

  pageSize = 10;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 20];
  pageEvent: PageEvent | null = null;

  displayedColumns: string[] = ['identifier', 'storeName', 'document', 'card', 'value', 'date'];
  dataSource = new MatTableDataSource<Transaction>();

  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;

  constructor(
    private transactionService: TransactionsService,
    private spinnerService: SpinnerService
  ) {

  }

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource<Transaction>();
  }

  ngOnInit(): void {
    let connection = new signalR.HubConnectionBuilder()
      .withUrl("http://localhost:8080/chat")
      .build();

    connection.on("ReceiveMessage", (user, message) => {
      console.log(user, message);
      this.GetTransactions();
    });

    connection.start()
      .then(() => connection.invoke("sendMessage", "charlesfranca", "Hello"))
      .catch(error => console.log(error.message));

    this.spinnerService.SpinnerSubscrition.subscribe(show => {
      this.showOverlay = show;
    })

    this.GetTransactions();
  }

  GetTransactions(pageIndex?: number, pageSize?: number) {
    this.spinnerService.SpinnerSubscrition.next(true);
    this.dataSource.data = [];
    this.transactionService.GetTransactions(pageIndex, pageSize).then(res => {
      if (this.paginator) {
        this.paginator.length = res.data.totalCount;
        this.paginator.pageIndex = pageIndex ? pageIndex - 1 : 0;
      }

      this.dataSource.data = res.data.items;
      this.dataSource._updatePaginator(res.data.totalCount);
      this.spinnerService.SpinnerSubscrition.next(false);
      // setTimeout(() => {
      //   this.spinnerService.SpinnerSubscrition.next(false);
      // }, 2000);
    });
  }

  handlePageEvent(e: PageEvent) {
    this.pageEvent = e;
    this.pageIndex = e.pageIndex + 1;
    console.log(this.pageEvent);
    this.GetTransactions(this.pageIndex, this.pageEvent.pageSize);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
}