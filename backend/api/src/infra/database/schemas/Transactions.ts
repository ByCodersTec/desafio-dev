import { DataTypes } from 'sequelize';
import { sequelize } from '../';
import { ParserStatus } from './ParserStatus';
import { Stores } from './Stores';
import { TransactionsTypes } from './TransactionsTypes';

export const Transactions = sequelize.define("Transactions", {
  id: {
    type: DataTypes.UUID,
    primaryKey: true
  },
  value: DataTypes.DECIMAL,
  occurence: DataTypes.DATE,
  buyerIdentification: DataTypes.STRING,
  creditCard: DataTypes.STRING,
}, {
  modelName: 'Transactions',
  tableName: 'transactions',
  timestamps: true
});

ParserStatus.hasMany(Transactions, {as: 'transactions', foreignKey: 'parserStatusId'});
Transactions.belongsTo(Stores, {as: 'store', foreignKey: 'storeId'});
Transactions.belongsTo(TransactionsTypes, {as: 'ttypes', foreignKey: 'transactionTypeId'});