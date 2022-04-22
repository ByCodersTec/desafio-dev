'use strict';

module.exports = {
  async up (queryInterface, Sequelize) {
    return queryInterface.createTable('TipoTransacao', {
      id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
      },
      tipo: {
        type: Sequelize.INTEGER,
        allowNull: false,
      },
      descricao: {
        type: Sequelize.STRING(30),
        allowNull: false,
      },
      natureza: {
        type: Sequelize.STRING(20),
        allowNull: false,
      },
      sinal: {
        type: Sequelize.STRING(2),
        allowNull: false,
      },
    })
  },

  async down (queryInterface, Sequelize) {
    return queryInterface.dropTable('TipoTransacao');
  }
};
