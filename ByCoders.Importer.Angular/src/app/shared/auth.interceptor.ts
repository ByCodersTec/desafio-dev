import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, switchMap } from 'rxjs';
import { JwtInterceptor } from '@auth0/angular-jwt';

import { environment } from 'src/environments/environment';
import { AuthenticationService } from './services/authentication.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    private refreshTokenRequest: Observable<string> | null = null;

    constructor(private authenticationService: AuthenticationService, private jwtInterceptor: JwtInterceptor) {}
  
    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
      if (request.url.indexOf(environment.apiUrl) >= 0 && !this.jwtInterceptor.isDisallowedRoute(request)
        && this.authenticationService.isTokenExpired()) {
  
        if (!this.refreshTokenRequest) {
          this.refreshTokenRequest = this.authenticationService.tryRefreshToken();
        }
  
        return this.refreshTokenRequest
          .pipe(
            switchMap(token => {
              this.refreshTokenRequest = null;
              
              return next.handle(
                request.clone({ setHeaders: { 'authorization': `Bearer ${token}` } })
              );
            })
          );
      }
      return next.handle(request);
    }
}