import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IStore } from './interfaces/IStore';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.apiUrl

  getStores() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    })

    return this.http.get<IStore[]>(`${this.baseUrl}/account/stores`, { headers })
  }

  upload(file: File)
  {
    const formData = new FormData(); 
  
    formData.append("file", file, file.name);
      
    return this.http.post(`${this.baseUrl}/file/import`, formData)
  }
}
