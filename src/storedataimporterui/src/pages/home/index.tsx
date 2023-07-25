import React, { useEffect, useState } from 'react';
import { PagedResponse } from '../../core/model/api-response';
import { Transaction } from '../../core/model/transaction';
import TransactionService from '../../core/services/transaction-service';
import './transaction.css';

function HomePage() {
  const [transactionsResponse, setTransactionsResponse] = useState<PagedResponse<Transaction>>();

  useEffect(() => {
    TransactionService.GetTransactions().then(res => {
      setTransactionsResponse(res as PagedResponse<Transaction>);
    })
  }, [])
    return (
      <div className="HomePage">
        <table>
          <thead>
            <th>Identifier</th>
            <th>Date</th>
            <th>Type</th>
            <th>Value</th>
            <th>Card</th>
            <th>Document</th>
            <th>Store Name</th>
          </thead>
          <tbody>
            {transactionsResponse && transactionsResponse.items && transactionsResponse.items.map((r) => (
              <tr
                key={r.identifier}
              >
                <td>{r.identifier}</td>
                <td>{r.date}</td>
                <td>{r.type}</td>
                <td>{r.value}</td>
                <td>{r.card}</td>
                <td>{r.document}</td>
                <td>{r.storeName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
  
  export default HomePage;