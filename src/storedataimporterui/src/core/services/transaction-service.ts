import HttpClient from "../http/http-client";
import { ApiResponse, PagedResponse } from "../model/api-response";
import { Transaction } from "../model/transaction";

export default class TransactionService {
    private static baseUrl = "http://localhost:8080/Transaction";

    public static GetTransactions(pageNumber: number = 1, pageSize: number = 10) {
        return new Promise(async (resolve, reject) => {
            const response = await HttpClient.getInstance().get<ApiResponse<PagedResponse<Transaction>>>(this.baseUrl + `?pageNumber=${pageNumber}&pageSize=${pageSize}&orderBy=Date&direction=desc`);
            resolve(response.data.data);
        });
    }
}