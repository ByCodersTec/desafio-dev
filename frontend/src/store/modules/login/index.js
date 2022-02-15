import { LoginActions } from './types';

const INITIAL_STATE = {
  data: {},
  error: false,
  loading: false,
};

const reducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case LoginActions.GET_LOGIN:
      return { ...state, loading: true };
    case LoginActions.SUCCCES_LOGIN:
      return {
      ...state, loading: false, error: null, data: action.payload.data,
      };
    case LoginActions.ERROR_LOGIN:
      return {
      ...state, loading: false, error: action.payload, data: {},
      };
    default:
      return state;
  }
};

export default reducer;