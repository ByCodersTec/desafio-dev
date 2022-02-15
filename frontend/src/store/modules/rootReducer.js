import { combineReducers } from 'redux';

import login from './login';
import user from './user';
import stores from './stores';

export default combineReducers({
  login,
  user,
  stores
});