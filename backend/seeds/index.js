import TransactionTypes from './infra/database/seeds/TransactionsTypes';

const seeds = [
  TransactionTypes
];


for (let seed of seeds) {
  try {
    seed()
  } catch (err) {
    console.error(err.message);
    continue
  }
}