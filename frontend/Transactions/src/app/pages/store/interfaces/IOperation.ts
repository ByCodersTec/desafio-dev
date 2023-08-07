export interface IOperationDetails {
    storeName: string,
    balance: number,
    operations: IOperation[]
}


export interface IOperation {
    id: string,
    transaction: string,
    date: string,
    value: number,
    document: string,
    card: string
}