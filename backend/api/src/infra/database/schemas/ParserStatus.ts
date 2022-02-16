import { DataTypes } from 'sequelize';
import { sequelize } from '../index';


export const ParserStatus = sequelize.define("ParserStatus", {
  id: {
    type: DataTypes.UUID,
    primaryKey: true
  },
  hasher: DataTypes.UUID,
  count: DataTypes.INTEGER,
  message: DataTypes.TEXT,
  status: {
    type: DataTypes.STRING,
    values: ["pending", "processing", "error", "complete"],
    defaultValue: "pending"
  }
}, { modelName: 'ParserStatus', tableName: 'parser_status' });
