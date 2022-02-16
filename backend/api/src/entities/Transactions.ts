import { Transactions as Schema } from '../infra/database/schemas/Transactions';

export default class Transactions extends Schema {
  private readonly id;
  private transactionTypeId;
  private storeId;
  private value;
  private occurence;
  private buyerIdentification;
  private creditCard;
}