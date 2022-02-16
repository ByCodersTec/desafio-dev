import loginSaga from './login/sagas';
import userSaga from './user/sagas';
import storesSaga from './stores/sagas';
import portfolioSaga from './portfolio/sagas';
import portfolioUploadSaga from './portfolio/upload/sagas';
import portfolioDeleteSaga from './portfolio/delete/sagas';

const { all, fork } = require('redux-saga/effects');

export default function* rootSaga() {
  yield all([
    fork(loginSaga),
    fork(userSaga),
    fork(storesSaga),
    fork(portfolioSaga),
    fork(portfolioUploadSaga),
    fork(portfolioDeleteSaga),
  ]);
}