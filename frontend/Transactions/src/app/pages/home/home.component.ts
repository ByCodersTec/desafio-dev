import { Component, OnInit } from '@angular/core';
import { IStore } from './interfaces/IStore';
import { HomeService } from './home.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  file: File | any = null;
  allowUpload = true;

  displayedColumns: string[] = ['storeName', 'storeOwnerName', 'balance', 'details'];
  stores: IStore[] = []

  USDollar = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
  });

  constructor(private homeService: HomeService, private router: Router) { }

  ngOnInit(): void {
    this.getStores()
  }

  onChange(event: any) {
    this.file = event.target.files[0];
  } 

  onUpload() {
    this.allowUpload = false

    this.homeService.upload(this.file).subscribe((event: any) => {
      this.getStores()
      this.allowUpload = true
    },
    error => {
      window.alert(error?.error?.message)
      this.allowUpload = true
    })
  }

  getStores()
  {
    this.homeService.getStores()
    .subscribe(data => {
      this.stores = data
    },
    error => window.alert(error?.error?.message))
  }

  getOperationsByStoreId(storeId: string)
  {
    this.router.navigate([`/store/${storeId}`]);
  }
}
