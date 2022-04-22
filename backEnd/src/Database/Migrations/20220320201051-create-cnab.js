'use strict';

module.exports = {
  async up (queryInterface, Sequelize) {
    return queryInterface.createTable('CNAB', {
      id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
      },
      tipoTransacao: {
        type: Sequelize.INTEGER,
        allowNull: false,
      },
      data: {
        type: Sequelize.STRING(8),
        allowNull: false,
      },
      valor: {
        type: Sequelize.DECIMAL(6, 2),
        allowNull: false,
      },
      cpf: {
        type: Sequelize.STRING(14),
        allowNull: false,
      },
      cartao: {
        type: Sequelize.STRING(12),
        allowNull: false,
      },
      hora: {
        type: Sequelize.STRING(6),
        allowNull: false,
      },
      donoLoja: {
        type: Sequelize.STRING(30),
        allowNull: false,
      },
      nomeLoja: {
        type: Sequelize.STRING(30),
        allowNull: false,
      },
      dataUpload: {
        type: Sequelize.DATE,
        allowNull: false,
      },
    })
  },

  async down (queryInterface, Sequelize) {
    return queryInterface.dropTable('CNAB');
  }
};
