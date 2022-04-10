import React, { useState, useEffect } from 'react';
import Select from "react-select";
import { FaStoreAlt, FaUpload, FaSpinner } from 'react-icons/fa';
import { useHistory } from 'react-router-dom';
import api from '../../services/api';
import Button from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'

import Container from '../../components/Container';

const Main = (props) => {
  const history = useHistory();

  const [stores, setStores] = useState([])
  const [store, setStore] = useState([])
  const [transactions, setTransactions] = useState([])
  const [sum, setSum] = useState(0)

  useEffect(() => {
    (async () => {
      const resp = await api.get("/stores")

      setStores(resp.data.response.map(e => ({ "label": e.name, "value": e.id })))
    })();
  }, []);

  const changeTransactions = async(e) => {
    setStore(e)
    const resp = await api.get(`/transactions/store/${e.value}`)
    setTransactions(resp.data.response)
    setSum(resp.data.value_sum)
  }

    return (
      <Container>
        <h1>
          <FaStoreAlt />
          Stores
        </h1>
        <Select style={{marginTop:'30px'}}
        value={store}
        closeMenuOnSelect={true}
        options={stores}
        placeholder="Select the Store"
        onChange={e => changeTransactions(e)} />

        <Table responsive style={{marginTop:'30px'}}>
        <thead>
          <tr>
            <th>Transaction Type</th>
            <th>CPF</th>
            <th>Card</th>
            <th>Date/Hour</th>
            <th>Value</th>
          </tr>
        </thead>
        <tbody>
          {transactions.length > 0 ? (transactions.map(t =>(
            <tr>
              <td>{t.transaction_type}</td>
              <td>{t.cpf}</td>
              <td>{t.card}</td>
              <td>{t.hour}</td>
              <td>{ t.transaction_type.includes('Entrada') ? '+' : '-' }  R${t.value}</td>
            </tr>
          )) ): (<FaSpinner/>)
          }
        </tbody>
        </Table>
        <h4 style={{ float: 'right'}}>TOTAL: R${sum.toFixed(2)}</h4>
        <Button style={{ marginTop:'30px', textAlign:'center', justifyContent: 'center'}}
            onClick={async () => {
                history.push('/upload')
            }}
            variant="warning"
            size="lg"
            round="true"
            icon="true"
            className='btn-round-acoes mr-3'
            title="Upload"

            > 
            <FaUpload/>
              Upload CNAB files
        </Button>
      </Container>
    );
  
}

export default Main;
