import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CnabModule } from './cnab/cnab.module';
import { SharedModule } from '../shared/shared-module';
import { MatMenuModule } from '@angular/material/menu';
import { PagesComponent } from './pages.component';
import { PagesRoutingModule, routes } from './pages-routing.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    PagesComponent,
  ],
  imports: [
    CnabModule,
    SharedModule,
    PagesRoutingModule,
    RouterModule.forChild(routes),
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PagesModule { }
