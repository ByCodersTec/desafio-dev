import { Transform } from 'class-transformer';

export class TransactionEntity {
  id: number;
  transactionTypeId: number;
  date: Date;

  @Transform(({ value }) => roundToTwo(value / 100))
  value: number;

  CPF: string;
  card: string;
  storeId: string;
}

// Javascript Hack to Round number to 2 Digits
const roundToTwo = (val: number) => {
  return +(Math.round(Number(val + 'e+2')) + 'e-2');
};
