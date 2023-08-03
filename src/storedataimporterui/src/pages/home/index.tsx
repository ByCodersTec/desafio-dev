import React, { useEffect, useState } from 'react';
import { PagedResponse } from '../../core/model/api-response';
import { Transaction } from '../../core/model/transaction';
import TransactionService from '../../core/services/transaction-service';
import './transaction.css';
import { useCallback } from 'react';
import Dropzone, { useDropzone } from 'react-dropzone';
import DateHelper from '../../core/helpers/date-helper';

function HomePage() {
  const [transactionsResponse, setTransactionsResponse] = useState<PagedResponse<Transaction>>();
  const [selectedFile, setSelectedFile] = React.useState<any>(null);
  const [currentPage, setCurrentPage] = React.useState<any>(1);
  const [pageSize, setPageSize] = React.useState<any>(15);

  const onDrop = useCallback((acceptedFiles: any) => {
    console.log(acceptedFiles);
  }, [])
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop })

  const loadTransactions = () => {
    TransactionService.GetTransactions(currentPage, pageSize).then(res => {
      setTransactionsResponse(res as PagedResponse<Transaction>);
    })
  }

  useEffect(() => {
    loadTransactions();
  }, []);

  useEffect(() => {
    loadTransactions();
  }, [pageSize, currentPage]);

  const handleSubmit = async (event: any) => {
    event.preventDefault()
    const formData = new FormData();
    formData.append("selectedFile", selectedFile);
    try {
      TransactionService.UploadCnaeTransactionsFile(selectedFile).then(res => {
        console.log(res);
      })
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <div className="HomePage">
      <Dropzone onDrop={acceptedFiles => setSelectedFile(acceptedFiles[0])}>
        {({ getRootProps, getInputProps }) => (
          <section>
            <div {...getRootProps()}>
              <input {...getInputProps()} />
              <p className='dropboxcontainer'>Drag 'n' drop some files here, or click to select files</p>
            </div>
          </section>
        )}
      </Dropzone>
      <div>
        Selected File: {selectedFile?.name || ''}
      </div>
      <br />
      <div>
        <button
          onClick={handleSubmit}
        >
          Submit
        </button>
      </div>
      <br />
      <div>
        <label>Per Page</label>
        <select
          onChange={(event: React.ChangeEvent<HTMLSelectElement>) => {
            setPageSize(event.target.value)
          }}
        >
          <option selected={pageSize == 10} value="10">10</option>
          <option selected={pageSize == 15} value="15">15</option>
          <option selected={pageSize == 30} value="30">30</option>
        </select>
      </div>
      <br />
      <table>
        <thead>
          <tr>
            <th>Identifier</th>
            <th>Date</th>
            <th>Type</th>
            <th>Value</th>
            <th>Card</th>
            <th>Document</th>
            <th>Store Name</th>
          </tr>
        </thead>
        <tbody>
          {transactionsResponse && transactionsResponse.items && transactionsResponse.items.map((r) => (
            <tr
              key={r.identifier}
            >
              <td>{r.identifier.substring(0, 10)}...</td>
              <td>{DateHelper.format(r.date)}</td>
              <td>{r.type}</td>
              <td>{r.value}</td>
              <td>{r.card}</td>
              <td>{r.document}</td>
              <td>{r.storeName}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div>
        <br />
        <button
          disabled={transactionsResponse && !transactionsResponse.hasPrivious}
          onClick={() => setCurrentPage((currentPage - 1))}
        >
          Previous
        </button>
        <button
          disabled={transactionsResponse && !transactionsResponse.hasNext}
          onClick={() => setCurrentPage((currentPage + 1))}
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default HomePage;