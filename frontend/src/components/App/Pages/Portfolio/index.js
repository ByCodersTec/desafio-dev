import { useEffect, useState } from "react";
import useSWR from 'swr'


import { Table, TableHeader, TableHeaderItem, TableBody, TableRow, TableColumn } from "./table";
import { Container, PageHeader, Contents } from '../styles';

import { PortfolioStatus } from "./styles"

import Preloader from "../../../Preloader";
import { Link } from "react-router-dom";

const fetcher = (url) => fetch(url).then((res) => res.json());

const PortfolioPage = () => {

    const { data } = useSWR('/parser-status', fetcher, { refreshInterval: 1000 });
    const [portfolio, setPortfolio] = useState([]);

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

    return <Container>
        {!data && <Preloader />}
        <PageHeader>
            <div className="page-title">
                <h1>Portfolio</h1>
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
                            </TableRow>
                        })}
                    </>
                </TableBody>
            </Table>
        </Contents>
    </Container>;
}

export default PortfolioPage;