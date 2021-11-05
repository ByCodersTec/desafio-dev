import React from "react";

const TransactionsList = ({ list }) => {
    return (
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Número Cartão</th>
                    <th>Loja / Dono</th>
                    <th>CPF</th>
                    <th>Tipo</th>
                    <th>Data Ocorrência</th>
                    <th>Data Importação</th>
                </tr>
            </thead>
            <tbody>
                {
                    list.map((t) => {
                        return (
                            <tr key={t.id}>
                                <td>{t.id}</td>
                                <td>{t.numCard}</td>
                                <td>{t.owner.name} / {t.store.name}</td>
                                <td>{t.document.numDocument}</td>
                                <td>{t.transactionType}</td>
                                <td>{t.dateOccurrence}</td>
                                <td>{t.dateCreation}</td>
                            </tr>
                        )
                    })
                }
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
        </table>
    )
}

export default TransactionsList;