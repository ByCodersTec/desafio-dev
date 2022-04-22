'use strict';

module.exports = {
  up: (queryInterface, Sequelize) =>
  {
    return queryInterface.createTable('Log', {
      id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
      },
      metodo: {
        type: Sequelize.STRING(10),
        allowNull: false,
      },
      url: {
        type: Sequelize.STRING(100),
        allowNull: false,
      },
      parametro: {
        type: Sequelize.STRING(1000),
        allowNull: false,
      },
      retorno: {
        type: Sequelize.STRING(8000),
        allowNull: false,
      },
      data: {
        type: Sequelize.DATE,
        allowNull: false,
      },
    })
  },

  down: (queryInterface) =>
  {
    return queryInterface.dropTable('Log');
  }
};
