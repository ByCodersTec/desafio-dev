import ActionsTypes from './types';

export const Dispatch = (file) => {
  return {
    type: ActionsTypes.GET_REQUEST,
    payload: { file },
  }
}

export const Success = data => {
  return {
    type: ActionsTypes.SUCCCES,
    payload: { uploaded: true, data },
  }
}
export const Error = error => {
  return {
    type: ActionsTypes.ERROR,
    payload: error,
  }
}