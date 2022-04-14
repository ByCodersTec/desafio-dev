import { NgModule } from '@angular/core';
import { Routes, RouterModule, ExtraOptions } from '@angular/router';

const routes: Routes = [
  {
    path: 'pages',
    loadChildren: () => import('./pages/pages.module').then((pages) => pages.PagesModule),
  },
  {path:'', redirectTo:'pages',pathMatch: 'full'},
  {path: '**', redirectTo: 'pages'}

];

const config: ExtraOptions ={
  useHash: false,
  onSameUrlNavigation: 'reload'
}

@NgModule({
  imports: [RouterModule.forRoot(routes, config)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
