import useSWR from 'swr'
import { useEffect, useRef, useState } from "react";
import { connect } from 'react-redux';
import Swal from 'sweetalert2'

import { Table, TableHeader, TableHeaderItem, TableBody, TableRow, TableColumn } from "./table";
import { Container, PageHeader, Contents } from '../styles';

import { UploadFileContent, UploadFileMask, UploadFileButton, PortfolioStatus } from "./styles"

import Preloader from "../../../Preloader";
import { Link } from "react-router-dom";

import { Dispatch as UploadDispatch } from "../../../../store/modules/portfolio/upload/actions";
import { Dispatch as RemoveDispatch } from "../../../../store/modules/portfolio/delete/actions";

const fetcher = (url) => fetch(url).then((res) => res.json());

const PortfolioPage = ({ upload: { loading, error }, remove: { delete: deleteSuccess, error: deleteError }, UploadDispatch, RemoveDispatch }) => {

    const { data } = useSWR('/parser-status', fetcher, { refreshInterval: 1000 });
    const [portfolio, setPortfolio] = useState([]);
    const hiddenFileInput = useRef(null);

    useEffect(() => {
        if (data) {
            const summary = data.map(d => {
                d.sum = d.transactions.reduce((a, b) => {
                    return a + parseFloat(b.value)
                }, 0)
                return d
            })
            setPortfolio(summary);
        }
    }, [data]);

    useEffect(() => {
        if (error) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: error,
              })
        }
    }, [error]);

    useEffect(() => {
        if (deleteSuccess) {
            Swal.fire('Deleted!', '', 'success')
        }

        if (deleteError) {
            Swal.fire(deleteError, '', 'error')
        }
    }, [deleteSuccess, deleteError]);

    const handleDelete = (id) => {
        Swal.fire({
            title: 'Do you want to remove?',
            showCancelButton: true,
            confirmButtonText: 'Remove',
          }).then((result) => {
            if (result.isConfirmed) {
              RemoveDispatch(id)
            }
          })
    }

    const handleClick = event => {
        hiddenFileInput.current.click();
    };

    const handleUploadFile = ({ target: { files } }) => {
        UploadDispatch(files[0])
    }

    return <Container>
        {(!data || loading) && <Preloader />}
        <PageHeader>
            <div className="page-title">
                <h1>Portfolio</h1>
            </div>
            <div className="page-actions">
                <UploadFileContent>
                    <UploadFileMask onClick={handleClick}>
                        Upload new block
                    </UploadFileMask>
                    <UploadFileButton type="file" name="file" ref={hiddenFileInput} onChange={handleUploadFile} />
                </UploadFileContent>
            </div>
        </PageHeader>
        <Contents>
            <Table cellSpacing={0} cellPadding={0}>
                <TableHeader>
                    <TableRow>
                        <TableHeaderItem>Id</TableHeaderItem>
                        <TableHeaderItem>Hasher</TableHeaderItem>
                        <TableHeaderItem>Transactions</TableHeaderItem>
                        <TableHeaderItem>Amount</TableHeaderItem>
                        <TableHeaderItem>Status</TableHeaderItem>
                        <TableHeaderItem></TableHeaderItem>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    <>
                        {portfolio.map((pf, i) => {
                            return <TableRow key={i}>
                                <TableColumn>
                                    <Link to={`/app/portfolio-details/${pf.id}`}>{pf.id}</Link>
                                </TableColumn>
                                <TableColumn>{pf.hasher}</TableColumn>
                                <TableColumn>{pf.count}</TableColumn>
                                <TableColumn>R${pf.sum}</TableColumn>
                                <TableColumn>
                                    <PortfolioStatus status={pf.status}>
                                        {pf.status}
                                    </PortfolioStatus>
                                </TableColumn>
                                <TableColumn>
                                    <button type='button' onClick={() => handleDelete(pf.id)} className='remove-button'>X</button>
                                </TableColumn>
                            </TableRow>
                        })}
                    </>
                </TableBody>
            </Table>
        </Contents>
    </Container>;
}

const mapStateToProps = (state) => {
    return { upload: state.portfolioUpload, remove: state.portfolioDelete }
};
  
export default connect(mapStateToProps, { UploadDispatch, RemoveDispatch })(PortfolioPage);