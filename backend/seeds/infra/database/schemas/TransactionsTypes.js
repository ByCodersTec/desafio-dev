import { DataTypes } from 'sequelize';
import sequelize from '../connection';

export const TransactionsTypes = sequelize.define("TransactionsTypes", {
  id: {
    type: DataTypes.UUID,
    primaryKey: true
  },
  code: {
    type: DataTypes.INTEGER,
    unique: true
  },
  label: DataTypes.STRING,
  type: {
    type: DataTypes.STRING,
    values: ["in", "out"]
  },

}, { modelName: 'TransactionsTypes', tableName: 'transactions_types', timestamps: true });
