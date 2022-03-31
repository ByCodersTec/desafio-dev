import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { catchError, delay, map, of, tap, Observable, lastValueFrom } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginResponse } from '../models/authentication/login-response';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
    private baseUrl = `${environment.apiUrl}/Authentication`;


    private get username(): string | null {
      return localStorage.getItem('username');
    }
    private set username(value: string | null) {
      if (value) {
        localStorage.setItem('username', value);
      }
      else {
        localStorage.removeItem('username');
      }
    }
  
    private get token(): string | null {
      return localStorage.getItem('jwt');
    }
    private set token(value: string | null) {
      if (value) {
        localStorage.setItem('jwt', value);
      }
      else {
        localStorage.removeItem('jwt');
      }
    }
  
    private get refreshToken(): string | null {
      return localStorage.getItem('refreshToken');
    }
    private set refreshToken(value: string | null) {
      if (value) {
        localStorage.setItem('refreshToken', value);
      }
      else {
        localStorage.removeItem('refreshToken');
      }
    }
  
  
    constructor(private router: Router, private http: HttpClient, private jwtHelper: JwtHelperService) {
    }
  
  
    public isAuthenticated(): boolean {
      return this.username !== null;
    }
  
    public isTokenExpired(): boolean {
      return (this.token !== null && this.jwtHelper.isTokenExpired(this.token));
    }
  
    public getInfo(): LoginResponse {
      return JSON.parse(atob(localStorage.getItem('userInfo') ?? ''));
  }
  
  
    public async authenticate(model: { username: string, password: string}): Promise<string> {
      return await lastValueFrom(this.http
        .post<LoginResponse>(`${this.baseUrl}/SignIn`, model)
        .pipe(
          tap(res => {
            this.username = res.username;
            this.token = res.token;
            this.refreshToken = res.refreshToken;
  
            of('').pipe(delay(2000)).subscribe(() => {
                this.router.navigate(["/"]);
            });
          }),
          map(() => ''),
  
          catchError((error: HttpErrorResponse) => {
            switch(error.status) {
              case 401:
                return of('InvalidUsernamePassword').pipe(delay(5000));
              default:
                console.log(error);
                return of('InternalServerError').pipe(delay(5000));
            }
          })
        ));
    }
  
    public tryRefreshToken(): Observable<string> {
      return this.http
        .post<LoginResponse>(`${this.baseUrl}/RefreshToken`, {
          username: this.username ?? '',
          refreshToken: this.refreshToken ?? ''
        })
        .pipe(
          tap(res => {
            this.token = res.token;
            this.refreshToken = res.refreshToken;
  
            return this.token;
          }),
          map(res => res.token),
          catchError(() => {
            return of('').pipe(delay(5000));
          })
        );
    }
  
    public signOut(): void {
      this.username = null;
      this.token = null;
      this.refreshToken = null;
      
      this.router.navigate(["/login"]);
    }
}