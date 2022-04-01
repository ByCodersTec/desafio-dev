import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ComponentsModule } from './../shared/components/components.module';
import { LoginComponent } from './authentication/login/login.component';
import { ImporterComponent } from './importer/importer.component';


@NgModule({
  declarations: [
    LoginComponent,
    ImporterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ComponentsModule
  ]
})
export class PagesModule { }