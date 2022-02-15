import ActionsTypes from './types';
import { Success, Error } from './actions';

const { call, put, takeEvery } = require('redux-saga/effects');

function fetchUser() {
  return fetch("https://random-data-api.com/api/users/random_user?size=1", { method: 'GET'})
    .then(response => response.json())
     .then(response => response);
}

function* getLoginSaga() {
  try {
    const user = yield call(fetchUser);
    yield put(Success(user[0]));
  } catch (err) {
    yield put(Error(err.message));
  }
}

function* loginSaga() {
  yield takeEvery(ActionsTypes.GET_REQUEST, getLoginSaga)
}

export default loginSaga;