import loginSaga from './login/sagas';

const { all, fork } = require('redux-saga/effects');

export default function* rootSaga() {
  yield all([
    fork(loginSaga),
  ]);
}