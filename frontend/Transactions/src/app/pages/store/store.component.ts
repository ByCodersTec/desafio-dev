import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IOperation } from './interfaces/IOperation';
import { StoreService } from './store.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

  storeId: string = "";
  displayedColumns: string[] = ['transaction', 'card', 'document', 'value', 'date'];
  operations: IOperation[] = []
  storeName: string = ""
  balance: number = 0

  USDollar = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
  });

  constructor(private route: ActivatedRoute, private storeService: StoreService, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=> {
      this.storeId = params['storeId'];    
    })

    this.storeService.getOperationsByStoreId(this.storeId)
    .subscribe(data => {
      this.storeName = data.storeName
      this.balance = data.balance

      this.operations = data.operations.map(operation => {
        return {...operation, date: new Date(operation.date).toLocaleString("en-US")}
      })
    },
    error => window.alert(error?.error?.message))
  }

  goToHome()
  {
    this.router.navigate(["/"]);
  }

}
