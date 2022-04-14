import { CommonModule } from "@angular/common";
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ToastrModule } from "ngx-toastr";
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';

const LIB_MODULES = [
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatGridListModule,
]

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ToastrModule.forRoot(),
        ...LIB_MODULES,
    ],
    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ToastrModule,
        ...LIB_MODULES
    ],
    schemas: [ CUSTOM_ELEMENTS_SCHEMA]
})
export class SharedModule{}