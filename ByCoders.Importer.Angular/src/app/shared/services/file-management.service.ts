import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FileManagementService {
  private baseUrl = `${environment.apiUrl}/FileManagement`;

  constructor(private http: HttpClient) { }

  uploadFile(file: Blob): Observable<Object> {
    const data = new FormData();
    data.append('file', file);

    return this.http.post(`${this.baseUrl}/Upload`, data)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          switch(error.status) {
            case 400:
              throw 'InvalidFormData';
            default:
              throw 'InternalServerError';
            }
          }
        )
      );
  }
}