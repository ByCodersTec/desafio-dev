import { combineReducers } from 'redux';

import login from './login';
import user from './user';
import stores from './stores';
import portfolio from './portfolio';
import portfolioUpload from './portfolio/upload';
import portfolioDelete from './portfolio/delete';

export default combineReducers({
  login,
  user,
  stores,
  portfolio,
  portfolioUpload,
  portfolioDelete
});