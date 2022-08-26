import React, { useEffect, useState } from 'react';
import NumberFormat from 'react-number-format';
import Navigation from "../../Components/Navbar";
import Card from "../../Components/Card";
import backendUrl from "../../config.js";
import './styles.css'

const Transaction = () => {
    const [isLoaded, setIsLoaded] = useState(false)
    const [items, setItems] = useState([])

    useEffect(() => {  
        setIsLoaded(true)
        getTransactions()
    }, [])

    const getTransactions = () => {
        
        fetch(backendUrl + 'transaction')
            .then(res => res.json())
            .then(
                (result) => {
                    setItems(result.data)
                    console.log(result.data);
                },
                (error) => {
                    
                }
            )
    }

    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {

        return (
            <div className="container">
                <Navigation />
                <div className="content">
                <br />
                    <h1>Lista de Transações</h1>

                    <Card>
                        <br />
                        <table>
                            <thead>
                                <tr>
                                    <th style={{width:'15%'}}>Tipo</th>
                                    <th>Data</th>
                                    <th>Valor</th>
                                    <th>CPF</th>
                                    <th>Cartão</th>
                                    <th>Hora</th>
                                    <th>Dono da Loja</th>
                                    <th>Loja</th>
                                </tr>
                            </thead>
                            <tbody>
                            {items.length > 0 ? (
                                items.map(transaction => {
                                return (
                                    <tr key={transaction.id}>
                                        <td>{transaction.type_transaction.name}</td>
                                        <td>{transaction.date}</td>
                                        <td>
                                            <NumberFormat 
                                                thousandSeparator={"."}
                                                decimalSeparator={","}
                                                value={transaction.value}
                                                prefix={"R$ "}
                                                displayType={"text"}
                                            />
                                        </td>
                                        <td>{transaction.cpf}</td>
                                        <td>{transaction.card}</td>
                                        <td>{transaction.hour}</td>
                                        <td>{transaction.store_owner}</td>
                                        <td>{transaction.store_name}</td>
                                    </tr>
                                )
                            })) : (
                                <tr>
                                    <td colSpan={8}>
                                        Carregando ...
                                    </td>
                                </tr>
                            )}
                            </tbody>
                        </table>
                    </Card>
            
                </div>
            </div>
        )
    }
}

export default Transaction;