import React from "react"
import Navigation from "../../Components/Navbar";
import Card from "../../Components/Card";
import './styles.css'

const Home = () => {
    return (
        <div className="container">
            <Navigation />

            <div className="content">
                <br />
                <h1>Dashboard</h1>

                <Card>
                    <h3>Resumo de Transações</h3>
                    <br />
                    <table>
                        <tbody>
                            <tr>
                                <td style={{width:'30%'}}>Arquivos importados</td>
                                <td>55</td>
                                <td style={{width:'30%'}}>Total de Registros Importados</td>
                                <td>221</td>
                            </tr>
                            <tr>
                                <td>Total de Lojas</td>
                                <td>7</td>
                                <td>Total de Crédito</td>
                                <td>R$ 1521,33 </td>
                            </tr>
                            <tr>
                                <td>Total de Débito</td>
                                <td>R$ 1521,33</td>
                                <td>Saldo</td>
                                <td>R$ 1521,33</td>
                            </tr>
                        </tbody>
                    </table>
                </Card>
        
            </div>
        </div>
        
    )
}

export default Home;