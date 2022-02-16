import { DataTypes } from 'sequelize';
import { sequelize } from '../index';


export const Stores = sequelize.define("Stores", {
  id: {
    type: DataTypes.UUID,
    primaryKey: true
  },
  storeOwner: DataTypes.STRING,
  storeName: DataTypes.STRING
}, { modelName: 'Stores', tableName: 'stores' });