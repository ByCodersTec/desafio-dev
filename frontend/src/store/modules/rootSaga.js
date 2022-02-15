import loginSaga from './login/sagas';
import userSaga from './user/sagas';

const { all, fork } = require('redux-saga/effects');

export default function* rootSaga() {
  yield all([
    fork(loginSaga),
    fork(userSaga),
  ]);
}