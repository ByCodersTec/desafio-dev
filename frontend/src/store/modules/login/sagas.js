import { LoginActions } from './types';
import API from '../../../providers/api';
import { LoginSuccess, LoginError } from './actions';

const { call, put, takeEvery } = require('redux-saga/effects');

function* getLoginSaga({ payload: { credentials, history } }) {
  try {
    const login = yield call(API.post, '/authenticate', credentials);
    yield put(LoginSuccess({...login.data.token}));
    
    localStorage.setItem("token", login.data.token);
    history.push("/");

  } catch (err) {
    yield put(LoginError(err.message));
  }
}

function* loginSaga() {
  yield takeEvery(LoginActions.GET_LOGIN, getLoginSaga)
}

export default loginSaga;