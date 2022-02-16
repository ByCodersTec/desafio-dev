import ActionsTypes from './types';
import API from '../../../providers/api';

import { Success, Error } from './actions';

const { call, put, takeEvery } = require('redux-saga/effects');

function summaryData(data) {
  data.sum = data.transactions.reduce((a, b) => {
    return a + parseFloat(b.value)
  }, 0)
  return data;
}

function* getPortfolioSaga({ payload: { id } }) {
  try {
    const portfolio = yield call(API.get, `/parser-status/${id}`);
    const data = summaryData(portfolio.data);

    yield put(Success(data));
  } catch (err) {
    yield put(Error(err.message));
  }
}

function* portfolioSaga() {
  yield takeEvery(ActionsTypes.GET_REQUEST, getPortfolioSaga)
}

export default portfolioSaga;