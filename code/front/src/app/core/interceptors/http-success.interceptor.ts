import { HttpEvent, HttpEventType, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { Observable } from "rxjs";
import { map } from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class HttpSuccessInterceptor implements HttpInterceptor{
    constructor(private toastService: ToastrService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next
            .handle(req)
            .pipe(
                map((event: HttpEvent<any>): any => {
                    const statusPossiveis = ['POST', 'PUT', 'DELETE'];
                    if(event.type === HttpEventType.Response && 
                        statusPossiveis.includes(req.method) ){
                        this.toastService.show('Transação efetuada com sucesso!');
                    }
                }
            )
        )
    }
}