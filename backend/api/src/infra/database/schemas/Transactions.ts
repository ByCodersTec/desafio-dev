import { Model, DataTypes } from 'sequelize';
import { sequelize } from '../';

export class Transactions extends Model {}
Transactions.init({
  id: {
    type: DataTypes.UUID,
    primaryKey: true
  },
  transactionTypeId: DataTypes.UUID,
  storeId: DataTypes.UUID,
  value: DataTypes.DECIMAL,
  occurence: DataTypes.DATE,
  buyerIdentification: DataTypes.NUMBER,
  creditCard: DataTypes.NUMBER,
}, { sequelize, modelName: 'transactions', timestamps: true });
