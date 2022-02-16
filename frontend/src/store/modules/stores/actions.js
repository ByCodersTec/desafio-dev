import ActionsTypes from './types';

export const Dispatch = () => {
  return {
    type: ActionsTypes.GET_REQUEST,
    payload: {},
  }
}

export const Success = data => {
  return {
    type: ActionsTypes.SUCCCES,
    payload: data,
  }
}
export const Error = error => {
  return {
    type: ActionsTypes.ERROR,
    payload: error,
  }
}