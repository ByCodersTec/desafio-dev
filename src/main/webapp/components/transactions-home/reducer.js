import { TRANSACTIONS_ERROR, TRANSACTIONS_SET, TRANSACTIONS_LOADING, CHANGE_FILE } from './constants'

const INITIAL_STATE = {
    isLoading: false,
    transactionsList: [],
    selectedStore: '',
    file: '',
    error: '',
}

export const transactionsReducer = (state = INITIAL_STATE, { type, payload }) => {
    switch (type) {
        case TRANSACTIONS_SET:
            return {
                ...state,
                transactionsList: payload,
                isLoading: false,
                file: ''
            };
        case TRANSACTIONS_ERROR:
            return {
                ...state,
                error: 'Houve um erro na requisição, tente novamente',
                isLoading: false
            };
        case TRANSACTIONS_LOADING:
            return {
                ...state,
                isLoading: true
            }
        case CHANGE_FILE:
            return {
                ...state,
                file: payload,
            }
        default:
            return state;
    }
}

export default transactionsReducer;