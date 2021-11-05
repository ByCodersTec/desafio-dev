import { createStore, applyMiddleware } from 'redux'
import thunk from 'redux-thunk'
import transactionsReducer from '../components/transactions-home/reducer'

const store = createStore(transactionsReducer, applyMiddleware(thunk))

export default store;