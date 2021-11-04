import React from 'react';
import ReactDOM from 'react-dom';
import { TransactionsHome } from './components/transactions-home';

const Main = () => <TransactionsHome />;

ReactDOM.render(
    <Main />,
    document.getElementById('react-app')
);