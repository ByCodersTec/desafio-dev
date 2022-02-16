import ActionsTypes from './types';
import API from '../../../../providers/api';

import { Success, Error } from './actions';

const { call, put, takeEvery } = require('redux-saga/effects');

function* getPortfolioUploadSaga({ payload: { file } }) {
  try {
    const buffer = new FormData();
    buffer.append('transactions', file);
    const upload = yield call(API.post, `/upload-to-parser`, buffer);
    yield put(Success(upload));
  } catch (err) {
    yield put(Error(err.message));
  }
}

function* portfolioUploadSaga() {
  yield takeEvery(ActionsTypes.GET_REQUEST, getPortfolioUploadSaga)
}

export default portfolioUploadSaga;