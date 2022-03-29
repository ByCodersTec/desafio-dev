interface OperationType {
  id: number;
  description: string;
  type: string;
}

export interface Cnab {
  id: null;
  operationType: OperationType;
  date: string;
  documentId: string;
  value: number;
  cardNumber: string;
  storeOwner: string;
  storeName: string;
}
