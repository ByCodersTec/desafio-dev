import React from "react";

const TransactionsList = () => {
    return (
        <table>
            <thead>
                <tr>
                    <th>ID Transação</th>
                    <th>Número Cartão</th>
                    <th>Nome da Loja</th>
                    <th>CPF</th>
                    <th>Tipo</th>
                    <th>Data Ocorrência</th>
                    <th>Data Importação</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>-----</td>
                    <td>-----</td>
                    <td>-----</td>
                    <td>-----</td>
                    <td>-----</td>
                    <td>-----</td>
                    <td>-----</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colSpan={6}>Total:</td>  
                    <td>R$ 100,00</td>
                </tr>
            </tfoot>
        </table>
    )
}

export default TransactionsList;