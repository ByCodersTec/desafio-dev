import Sequelize from 'sequelize';

import Database from '../Database/Index';

import CNAB from '../Models/CNAB';
import TipoTransacao from '../Models/TipoTransacao';
import Log from '../Models/Log';
const models = [
  Log,
  CNAB,
  TipoTransacao
];

export default class SequelizeConnection
{
  constructor()
  {
    this.init();
  }

  init()
  {
    this.connection = new Sequelize(Database);

    models
      .map(model => model.init(this.connection))
      .map(model => model.associate && model.associate(this.connection.models));
  }
}
