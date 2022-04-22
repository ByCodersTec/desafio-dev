'use strict';

module.exports = {
  async up (queryInterface, Sequelize) {
    return queryInterface.bulkInsert('TipoTransacao', [
      { 
        tipo: 1, 
        descricao: 'Débito',
        natureza: 'Entrada',
        sinal:'+'
      },
      { 
        tipo: 2, 
        descricao: 'Boleto' ,
        natureza: 'Saída',
        sinal:'-'
      },
      { 
        tipo: 3, 
        descricao: 'Financiamento' ,
        natureza: 'Saída',
        sinal:'-'
      },
      { 
        tipo: 4, 
        descricao: 'Crédito' ,
        natureza: 'Entrada',
        sinal:'+'
      },
      { 
        tipo: 5, 
        descricao: 'Recebimento Empréstimo',
        natureza: 'Entrada',
        sinal:'+' 
      },
      { 
        tipo: 6, 
        descricao: 'Vendas' ,
        natureza: 'Entrada',
        sinal:'+'
      },
      { 
        tipo: 7, 
        descricao: 'Recebimento TED',
        natureza: 'Entrada',
        sinal:'+' 
      },
      { 
        tipo: 8, 
        descricao: 'Recebimento DOC',
        natureza: 'Entrada',
        sinal:'+' 
      },
      { 
        tipo: 9, 
        descricao: 'Aluguel',
        natureza: 'Saída',
        sinal:'-' 
      },
    ], {});

  },

  async down (queryInterface, Sequelize) {
    return queryInterface.bulkDelete('TipoTransacao', null, {});
  }
};
