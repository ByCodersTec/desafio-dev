import ActionsTypes from './types';

export const Dispatch = (id) => {
  return {
    type: ActionsTypes.GET_REQUEST,
    payload: { id },
  }
}

export const Success = data => {
  return {
    type: ActionsTypes.SUCCCES,
    payload: true,
  }
}
export const Error = error => {
  return {
    type: ActionsTypes.ERROR,
    payload: error,
  }
}