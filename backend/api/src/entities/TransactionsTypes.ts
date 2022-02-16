import { TransactionsTypes as Schema } from '../infra/database/schemas/TransactionsTypes';

export default class TransactionsTypes extends Schema {
  private readonly id;
  private code;
  private label;
  private type;
}