import { Routes } from "@angular/router";
import { ConsultaComponent } from "./consulta/consulta.component";
import { UploadComponent } from "./upload/upload.component";

export const RouteCNAB: Routes = [
    {
        path: 'upload',
        component: UploadComponent
    },
    {
        path: 'consulta',
        component: ConsultaComponent
    }
]