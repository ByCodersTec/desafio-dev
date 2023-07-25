import axios from "axios";
import HttpRequestConfig from "./http-request-config";

export default class HttpClient {
    private static instance: HttpClient;
    private constructor() { }

    public static getInstance(): HttpClient {
        if (!HttpClient.instance) {
            HttpClient.instance = new HttpClient();
        }

        return HttpClient.instance;
    }
    
    public get<T>(url: string, config?: HttpRequestConfig<T>) {
        return axios.get<T>(url, config);
    }

    public post<T>(url: string, data: any, config?: HttpRequestConfig<T>) {
        return axios.post<T>(url, data, config);
    }
}