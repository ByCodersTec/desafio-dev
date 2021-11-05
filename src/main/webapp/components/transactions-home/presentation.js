import React from "react";
import { UploadForm } from "../upload-form";
import { Header } from "../header";
import { Footer } from "../footer"
import { TransactionsList } from "../transactions-list";
import "../../styles/global.css"

const TransactionsHome = () => {
    return (
        <body>
            <Header />
            <UploadForm />
            <TransactionsList />
            <Footer />
        </body>
    );
}

export default TransactionsHome;