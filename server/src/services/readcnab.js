const db = require('./db');
const fs = require('fs');
const lineByLine = require('n-readlines');

/* TODO:
 * Fiz a troca do fs.readFile pelo pacote n-readlines
 * devido pelo consumo de processamento e memoria comparado ao nativo.
 */

function cnabRead(fileTxt) {
  let data_line = [];
  try {
    const liner = new lineByLine(fileTxt.file.path);
    let line;

    while ((line = liner.next())) {
      data_line.push({
        tipo: typesTransactions(line.toString('utf-8').substring(0, 1)),
        data: line.toString('utf-8').substring(1, 9),
        valor: line.toString('utf-8').substring(10, 19),
        cpf: line.toString('utf-8').substring(19, 30),
        cartao: line.toString('utf-8').substring(30, 42),
        hora: line.toString('utf-8').substring(42, 48),
        proprietario: line.toString('utf-8').substring(48, 62),
        nome_da_loja: line.toString('utf-8').substring(62, 82)
      });
    }
    return createCnab(data_line);
  } catch (err) {
    console.error(`Error `, err.message);
  }
}
async function createCnab(cnab) {
  try {
    let result = await cnab.map((item) => {
      return db.query(
        'INSERT INTO cnabimport(type_transaction, create_at, transaction_value, cpf, credit_card, time_create, owner, store_name ) VALUES ($1, $2, $3, $4, $5, $6, $7, $8) RETURNING *',
        [item.tipo, item.data, item.valor, item.cpf, item.cartao, item.hora, item.proprietario, item.nome_da_loja]
      );
    });

    if (result.length) return { status: 200, message: 'Sucess' };
  } catch (error) {
    console.error(`Error `, error.message);
  }
}

function typesTransactions(types = 0) {
  try {
    let type = null;
    switch (types) {
      case '1':
        type = 'débito';
        break;
      case '2':
        type = 'boleto';
        break;
      case '3':
        type = 'financiamento';
        break;
      case '4':
        type = 'crédito';
        break;
      case '5':
        type = 'recebimento Empréstimo';
        break;
      case '6':
        type = 'vendas';
        break;
      case '7':
        type = 'recebimento TED';
        break;
      case '8':
        type = 'recebimento DOC';
        break;
      case '9':
        type = 'aluguel';
        break;
      default:
        type = 'sem classificação';
    }

    return type;
  } catch (error) {
    console.error(`Error `, error.message);
  }
}

async function getList() {
  try {
    const data = await db.query(
      'SELECT id, type_transaction, create_at, transaction_value, cpf, credit_card, time_create, owner, store_name FROM cnabimport'
    );

    return data;
  } catch (error) {
    console.error(`Error `, error.message);
  }
}

module.exports = {
  cnabRead,
  getList
};
