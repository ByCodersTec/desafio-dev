import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreComponent } from './store.component';
import { StoreRoutingModule } from './store-routing.module';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { StoreService } from './store.service';
import { MatButtonModule } from '@angular/material/button';


@NgModule({
  declarations: [
    StoreComponent
  ],
  imports: [
    CommonModule,
    StoreRoutingModule,
    MatTableModule,
    MatIconModule,
    HttpClientModule,
    MatButtonModule
  ],
  providers: [
    StoreService
  ]
})
export class StoreModule { }
