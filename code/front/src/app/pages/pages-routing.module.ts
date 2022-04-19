import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PagesComponent } from './pages.component';

export const routes: Routes = [
    {
        path: 'pages',
        component: PagesComponent
    },
    {
        path: 'cnab',
        loadChildren:() => import('./cnab/cnab.module')
            .then(upload => upload.CnabModule)
    },
    {path:'', redirectTo:'pages',pathMatch: 'full'},
    {path: '**', redirectTo: 'pages'}

];

@NgModule({
    imports: [CommonModule, RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PagesRoutingModule {}