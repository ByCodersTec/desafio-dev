import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from 'src/app/app-routing.module';
import { LoginInputComponent } from './login/login-input/login-input.component';


@NgModule({
  declarations: [
    LoginInputComponent
  ],
  imports: [
    AppRoutingModule,
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
      LoginInputComponent
  ]
})
export class ComponentsModule { }