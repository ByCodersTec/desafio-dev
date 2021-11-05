import React from 'react';
import ReactDOM from 'react-dom';
import { TransactionsHome } from './components/transactions-home';
import store from './store';
import { Provider } from 'react-redux';

const Main = () => (
    <Provider store={store}>
        <TransactionsHome />
    </Provider>
);

ReactDOM.render(
    <Main />,
    document.getElementById('react-app')
);