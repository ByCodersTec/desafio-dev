import { Model, DataTypes } from 'sequelize';
import { sequelize } from '../';

export class TransactionsTypes extends Model {}
TransactionsTypes.init({
  id: {
    type: DataTypes.UUID,
    primaryKey: true
  },
  code: DataTypes.INTEGER,
  label: DataTypes.STRING,
  type: {
    type: DataTypes.STRING,
    values: ["input", "output"]
  },

}, { sequelize, modelName: 'transactionsTypes', tableName: 'transactions_types', timestamps: true });
