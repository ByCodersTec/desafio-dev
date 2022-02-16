import ActionsTypes from './types';

const INITIAL_STATE = {
  portfolio: [],
  error: null,
  loading: false,
};

const reducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case ActionsTypes.GET_REQUEST:
      return { ...state, loading: true };
    case ActionsTypes.SUCCCES:
      return {
      ...state, loading: false, error: null, portfolio: action.payload,
      };
    case ActionsTypes.ERROR:
      return {
      ...state, loading: false, error: action.payload, portfolio: [],
      };
    default:
      return state;
  }
};

export default reducer;