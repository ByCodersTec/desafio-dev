import React, { useEffect, useState } from 'react';
import NumberFormat from 'react-number-format';
import Navigation from "../../Components/Navbar";
import Card from "../../Components/Card";
import backendUrl from "../../config.js";
import './styles.css'

const Home = () => {
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState({
        "totImports": "..",
        "totTransactions": "..",
        "totStores": "..",
        "totCredit": "..",
        "totDebit": "..",
        "totBalance": ".."
    });

    useEffect(() => {  
        setIsLoaded(true)
        getSummaryData()
    }, [])

    const getSummaryData = () => {
        
        fetch(backendUrl + 'summary')
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
                    <h1>Dashboard</h1>

                    <Card>
                        <h3>Resumo de Transações</h3>
                        <button className="btn" onClick={() => getSummaryData()}>atualizar</button>
                        <br />
                        <table>
                            <tbody>
                                <tr>
                                    <td style={{width:'30%'}}>Arquivos importados</td>
                                    <td>{items.totImports}</td>
                                    <td style={{width:'30%'}}>Total de Registros Importados</td>
                                    <td>{items.totTransactions}</td>
                                </tr>
                                <tr>
                                    <td>Total de Lojas</td>
                                    <td>{items.totStores}</td>
                                    <td>Total de Crédito</td>
                                    <td>
                                        <NumberFormat 
                                            thousandSeparator={"."}
                                            decimalSeparator={","}
                                            value={items.totCredit}
                                            prefix={"R$ "}
                                            displayType={"text"}
                                        />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Total de Débito</td>
                                    <td>
                                        <NumberFormat 
                                            thousandSeparator={"."}
                                            decimalSeparator={","}
                                            value={items.totDebit}
                                            prefix={"R$ "}
                                            displayType={"text"}
                                        />
                                    </td>
                                    <td>Saldo</td>
                                    <td>
                                        <NumberFormat 
                                            thousandSeparator={"."}
                                            decimalSeparator={","}
                                            value={items.totBalance}
                                            prefix={"R$ "}
                                            displayType={"text"}
                                        />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </Card>
            
                </div>
            </div>
            
        )
    }
}

export default Home;