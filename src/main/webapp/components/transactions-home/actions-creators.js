import axios from 'axios';
import { TRANSACTIONS_LOADING, TRANSACTIONS_ERROR, TRANSACTIONS_SET } from './constants';

const httpClient = axios.create({
    baseURL: 'http://localhost:8080'
});

export const init = () => async (dispatch) => {
    try {
        dispatch({
            type: TRANSACTIONS_LOADING,
        });

        const transactionsList = await httpClient.get('/transactions');

        dispatch({
            type: TRANSACTIONS_SET,
            payload: transactionsList,
        });

    } catch (error) {
        dispatch({
            type: TRANSACTIONS_ERROR,
            payload: error
        })
    }

}