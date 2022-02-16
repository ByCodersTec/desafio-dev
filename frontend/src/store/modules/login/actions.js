import { LoginActions } from './types';

export const getLoginDispatch = (credentials, history) => {
  return {
    type: LoginActions.GET_LOGIN,
    payload: { credentials, history },
  }
}

export const LoginSuccess = data => {
  return {
    type: LoginActions.SUCCCES_LOGIN,
    payload: data,
  }
}
export const LoginError = error => {
  return {
    type: LoginActions.ERROR_LOGIN,
    payload: error,
  }
}