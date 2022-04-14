import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UploadComponent } from './upload/upload.component';
import { ConsultaComponent } from './consulta/consulta.component';
import { SharedModule } from 'src/app/shared/shared-module';
import { RouteCNAB } from './cnab.routing.module';
import { RouterModule } from '@angular/router';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatIconModule} from '@angular/material/icon';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { SnackBarComponent } from './upload/snack-bar/snack-bar.component';
import { SnackBarNoFileComponent } from './upload/snack-bar/snack-bar-no-file.component';
import { SnackBarTypeFileErrorComponent } from './upload/snack-bar/snack-bar-type-file-error.component';

@NgModule({
  declarations: [UploadComponent, ConsultaComponent, SnackBarComponent, SnackBarNoFileComponent, SnackBarTypeFileErrorComponent],
  imports: [
    SharedModule,
    RouterModule.forChild(RouteCNAB),
    MatProgressBarModule,
    MatIconModule,
    MatSnackBarModule
  ]
})
export class CnabModule { }
