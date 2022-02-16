import ActionsTypes from './types';

const INITIAL_STATE = {
  upload: {},
  error: null,
  loading: false,
};

const reducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case ActionsTypes.GET_REQUEST:
      return { ...state, loading: true };
    case ActionsTypes.SUCCCES:
      return {
      ...state, loading: false, error: null, upload: action.payload,
      };
    case ActionsTypes.ERROR:
      return {
      ...state, loading: false, error: action.payload, upload: {},
      };
    default:
      return state;
  }
};

export default reducer;