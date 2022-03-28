import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from './../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  private API_URL: string = environment.API_URL;
  headers = new HttpHeaders().set('Content-Type', 'multipart/form-data').set('Accept', 'application/json');
  constructor(private http: HttpClient) {}

  upload(files: Set<File>, url: string) {
    const formData = new FormData();
    files.forEach((file) => formData.append('file', file, file.name));

    return this.http.post(url, formData, {
      observe: 'events',
      reportProgress: true
    });
  }
}
