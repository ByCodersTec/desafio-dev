import React, { useEffect } from "react";
import TransactionsHome from "./presentation";
import { useDispatch, useSelector } from "react-redux";
import { init, changeFile, submit } from "./actions-creators";
import { selectFile, selectTransactionsList } from './selectors'


const TransactionsHomeContainer = () => {
    const dispatch = useDispatch();
    const transactionsList = useSelector(selectTransactionsList);
    const file = useSelector(selectFile);

    const initHandler = () => {
        init()(dispatch);
    }

    const fileChangeHandler = (e) => {
        changeFile(e.target.files[0])(dispatch)
    }

    const submitHandler = () => {
        console.log('enviando lotes', file);
        submit(file)(dispatch);
    }

    useEffect(() => {
        initHandler();
    }, [])

    return (
        <TransactionsHome
            file={file}
            transactionsList={transactionsList}
            fileChangeHandler={fileChangeHandler}
            submitHandler={submitHandler}
        />
    )
}

export default TransactionsHomeContainer;
