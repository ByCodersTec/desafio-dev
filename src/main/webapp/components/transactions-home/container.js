import React, { useEffect } from "react";
import TransactionsHome from "./presentation";
import { useDispatch, useSelector } from "react-redux";
import { init } from "./actions-creators";


const TransactionsHomeContainer = () => {
    const dispatch = useDispatch();

    const initHandler = () => {
        init()(dispatch);
    }

    useEffect(() => {
        initHandler();
    },[])

    return (
        <TransactionsHome />
    )
}

export default TransactionsHomeContainer;
