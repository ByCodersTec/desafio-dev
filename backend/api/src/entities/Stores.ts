import { Stores as Schema } from '../infra/database/schemas/Stores';

export default class Stores extends Schema {
  private readonly id;
  private storeOwner;
  private storeName;
}