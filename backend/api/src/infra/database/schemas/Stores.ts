import { Model, DataTypes } from 'sequelize';
import { sequelize } from '../index';

export class Stores extends Model {}
Stores.init({
  id: {
    type: DataTypes.UUID,
    primaryKey: true
  },
  storeOwner: DataTypes.STRING,
  storeName: DataTypes.STRING
}, { sequelize, modelName: 'stores' });
