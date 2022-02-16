import { combineReducers } from 'redux';

import login from './login';
import user from './user';
import stores from './stores';
import portfolio from './portfolio';

export default combineReducers({
  login,
  user,
  stores,
  portfolio
});