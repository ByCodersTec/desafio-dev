import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SpinnerService } from './core/services/spinner.service';
import { TransactionsService } from './core/services/transactions.service';
import { HttpClientModule } from '@angular/common/http'
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatTableResponsiveModule } from './shared/directives/mat-table-responsive/mat-table-responsive.module';
import { MatSortModule } from '@angular/material/sort';
import { NgxDropzoneModule } from 'ngx-dropzone';
import { MatButtonModule } from '@angular/material/button';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatPaginatorModule,
    MatTableModule,
    MatProgressSpinnerModule,
    MatTableResponsiveModule,
    MatSortModule,
    NgxDropzoneModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  providers: [
    SpinnerService,
    TransactionsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
