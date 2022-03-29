import { BrowserModule } from '@angular/platform-browser';
import { DEFAULT_CURRENCY_CODE, NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { UploadFileComponent } from './components/upload-file/upload-file.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { TableGridParserComponent } from './components/table-grid-parser/table-grid-parser.component';
import { GuiGridModule } from '@generic-ui/ngx-grid';
import { HttpClientModule } from '@angular/common/http';
import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    UploadFileComponent,
    NavbarComponent,
    FooterComponent,
    TableGridParserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    GuiGridModule,
    HttpClientModule,
    RouterModule,
    AlertModule,
    AccordionModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [AlertConfig],
  bootstrap: [AppComponent]
})
export class AppModule {}
