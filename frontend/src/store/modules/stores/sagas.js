import ActionsTypes from './types';
import API from '../../../providers/api';

import { Success, Error } from './actions';

const { call, put, takeEvery } = require('redux-saga/effects');

function* getStoresSaga() {
  try {
    const stores = yield call(API.get, '/stores');
    yield put(Success(stores.data));
  } catch (err) {
    yield put(Error(err.message));
  }
}

function* storesSaga() {
  yield takeEvery(ActionsTypes.GET_REQUEST, getStoresSaga)
}

export default storesSaga;