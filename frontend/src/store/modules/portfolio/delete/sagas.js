import ActionsTypes from './types';
import API from '../../../../providers/api';

import { Success, Error } from './actions';

const { call, put, takeEvery } = require('redux-saga/effects');

function* getPortfolioDeleteSaga({ payload: { id } }) {
  try {
    yield call(API.remove, `/parser-status/${id}`);
    yield put(Success());
  } catch (err) {
    yield put(Error(err.message));
  }
}

function* portfolioDeleteSaga() {
  yield takeEvery(ActionsTypes.GET_REQUEST, getPortfolioDeleteSaga)
}

export default portfolioDeleteSaga;