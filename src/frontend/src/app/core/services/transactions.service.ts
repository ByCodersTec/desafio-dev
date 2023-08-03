import { Injectable } from '@angular/core';
import { ApiResponse, PagedResponse } from '../model/api-response';
import { Transaction } from '../model/transaction';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

  private baseUrl = "http://localhost:8080/Transaction";

  constructor(
    private httpClient: HttpClient
  ) {

  }

  public GetTransactions(pageNumber: number = 1, pageSize: number = 10): Promise<ApiResponse<PagedResponse<Transaction>>> {
    return new Promise(async (resolve, reject) => {
      this.httpClient.get<ApiResponse<PagedResponse<Transaction>>>(this.baseUrl + `?pageNumber=${pageNumber}&pageSize=${pageSize}&orderBy=Date&direction=desc`).subscribe(res => {
        resolve(res);
      });
    });
  }

  public UploadCnaeTransactionsFile(selectedFile: File) {
    return new Promise(async (resolve, reject) => {
      const formData = new FormData();
      formData.append("file", selectedFile);
      const request = {
        method: "post",
        headers: { "Content-Type": "multipart/form-data" },
      };
      console.log('selectedFile', selectedFile);
      this.httpClient.post(this.baseUrl + '/enqueue', formData, request).subscribe(res => {
        resolve(res);
      })
    })
  }
}
