export default interface HttpRequestConfig<T> {
    url?: string;
    method?: string;
    baseURL?: string;
    headers?: any;
    params?: any;
    data?: T;
}