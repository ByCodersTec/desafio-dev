import React, { useEffect, useState } from 'react';
import NumberFormat from 'react-number-format';
import Navigation from "../../Components/Navbar";
import Card from "../../Components/Card";
import backendUrl from "../../config.js";
import './styles.css'

const Store = () => {
    const [isLoaded, setIsLoaded] = useState(false)
    const [items, setItems] = useState([])
    const [stores, setStores] = useState([])
    const [storeSel, setStoreSel] = useState("");
    const [balance, setBalance] = useState(0);
    const [debits, setDebits] = useState(0);
    const [credits, setCredits] = useState(0);

    useEffect(() => {  
        setIsLoaded(true)
        getStores()
    }, [])

    const getStores = () => {
                
        fetch(backendUrl + 'store')
            .then(res => res.json())
            .then(
                (result) => {
                    setStores(result.data)
                    setStoreSel(result.data[0].store_name)
                },
                (error) => {
                    alert('erro ao buscar os dados')
                    console.log(error)
                }
            )
    }

    const getTransactions = () => {
        const formData = new FormData();
		formData.append('store', storeSel);
        
        fetch(backendUrl + 'transaction/store', 
                {
                    method: 'POST',
                    body: formData,
                })
            .then(res => res.json())
            .then(
                (result) => {
                    setItems(result.data)
                    let deb = getSumArrayData(result.data, 'value', "saida")
                    let cre = getSumArrayData(result.data, 'value', "entrada")
                    setDebits(deb)
                    setCredits(cre)
                    setBalance(cre - deb)
                },
                (error) => {
                    alert('erro ao buscar os dados')
                    console.log(error)
                }
            )
    }

    const getSumArrayData = (arr, key, type) => {
        let filtered = arr.filter(function (str) { return str.type_transaction.type === type; });
        return filtered.reduce((accumulator, current) => accumulator + Number(current[key]), 0)
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
                        <p>Selecione a Loja</p>
                        <select value={storeSel} onChange={(event) => { setStoreSel(event.target.value) }} className="select-store">
                            {stores.length > 0 && (
                                stores.map(store => {
                                    return(
                                        <option key={store.store_name} value={store.store_name}>{store.store_name}</option>
                                    )
                                })
                            )}
                        </select>

                        <input type={"button"} value={"Filtrar"} onClick={getTransactions} className="select-store mt" />
                        
                        <br />
                        <table>
                            <thead>
                                <tr>
                                    <th style={{width:'20%'}}>Tipo</th>
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
                                            <td>
                                                ({transaction.type_transaction.type === "saida" ? (<>-</>) : (<>+</>)}) 
                                                {transaction.type_transaction.name}
                                            </td>
                                            <td>{transaction.date}</td>
                                            <td>
                                                <NumberFormat 
                                                    thousandSeparator={"."}
                                                    decimalSeparator={","}
                                                    decimalScale={2}
                                                    fixedDecimalScale={true}
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
                                        Selecione a loja
                                    </td>
                                </tr>
                            )}
                            </tbody>
                            {items.length > 0 && (
                                <tfoot>
                                    <tr>
                                        <td colSpan={"6"} className="text-right">Total de Créditos</td>
                                        <td colSpan={"2"} className="text-center">
                                            <NumberFormat 
                                                    thousandSeparator={"."}
                                                    decimalSeparator={","}
                                                    decimalScale={2}
                                                    fixedDecimalScale={true}
                                                    value={credits}
                                                    prefix={"R$ "}
                                                    displayType={"text"}
                                                />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colSpan={"6"} className="text-right">Total de Débitos</td>
                                        <td colSpan={"2"} className="text-center">
                                            <NumberFormat 
                                                    thousandSeparator={"."}
                                                    decimalSeparator={","}
                                                    decimalScale={2}
                                                    fixedDecimalScale={true}
                                                    value={debits}
                                                    prefix={"R$ "}
                                                    displayType={"text"}
                                                />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colSpan={"6"} className="text-right">Total</td>
                                        <td colSpan={"2"} className="text-center">
                                            <NumberFormat 
                                                    thousandSeparator={"."}
                                                    decimalSeparator={","}
                                                    decimalScale={2}
                                                    fixedDecimalScale={true}
                                                    value={balance}
                                                    prefix={"R$ "}
                                                    displayType={"text"}
                                                />
                                        </td>
                                    </tr>
                                </tfoot>
                            )}
                        </table>
                    </Card>
            
                </div>
            </div>
        )
    }
}

export default Store;