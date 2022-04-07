import React, { useState, useEffect } from 'react';
import Select from "react-select";
import { FaGithubAlt, FaPlus, FaSpinner } from 'react-icons/fa';
import { useHistory } from 'react-router-dom';
import api from '../../services/api';
import Button from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'

import Container from '../../components/Container';
import { Form, SubmitButton, List } from './styles';

// import { Container } from './styles.css';

const Main = (props) => {
  const history = useHistory();

  // eslint-disable-next-line react/state-in-constructor
  const [loading, setLoading] = useState(false)
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
    console.log(e)
    const resp = await api.get(`/transactions/${e.value}`)
    console.log(resp)
    setTransactions(resp.data.response)
    setSum(resp.data.value_sum)
  }

    return (
      <Container>
        <h1>
          <FaGithubAlt />
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
          {transactions.map(t =>(
            <tr>
              <td>{t.transaction_type}</td>
              <td>{t.cpf}</td>
              <td>{t.card}</td>
              <td>{t.hour}</td>
              <td>R${t.value}</td>
            </tr>
          ))}
        </tbody>
        </Table>
        <h4 style={{ float: 'right'}}>Sum of values: R${sum},00</h4>
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
              Upload de arquivos CNAB
        </Button>
      </Container>
    );
  
}

export default Main;
