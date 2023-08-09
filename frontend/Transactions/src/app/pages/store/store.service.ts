import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IOperationDetails } from './interfaces/IOperation';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.apiUrl

  getOperationsByStoreId(storeId: string)
  {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    })

    return this.http.get<IOperationDetails>(`${this.baseUrl}/account/stores/${storeId}/operations`, { headers })
  }
}
