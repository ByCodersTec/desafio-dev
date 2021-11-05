import React from "react";
import { UploadForm } from "../upload-form";
import { Header } from "../header";
import { Footer } from "../footer"
import { TransactionsList } from "../transactions-list";
import "../../styles/global.css"

const TransactionsHome = ({transactionsList, submitHandler, fileChangeHandler, file}) => {
    return (
        <>
            <Header />
            <UploadForm
                file={file}
                fileChangeHandler={fileChangeHandler}
                submitHandler={submitHandler}
            />
            <TransactionsList list={transactionsList}/>
            <Footer />
        </>
    );
}

export default TransactionsHome;