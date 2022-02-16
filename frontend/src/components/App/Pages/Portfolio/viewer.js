import { useEffect } from "react";
import { connect } from 'react-redux';

import { Table, TableHeader, TableHeaderItem, TableBody, TableRow, TableColumn } from "./table";
import { Container, PageHeader, Contents } from '../styles';

import { TypeStatus } from "./styles"

import Preloader from "../../../Preloader";

import { Dispatch as PortfolioDispatch } from "../../../../store/modules/portfolio/actions";
import { useParams } from "react-router-dom";


const PortfolioViewerPage = ({ loading, portfolio, PortfolioDispatch }) => {

  const { id } = useParams();

  useEffect(() => {
    PortfolioDispatch(id);
  }, [PortfolioDispatch, id]);

  console.log(portfolio)
  return <Container>
    {loading && <Preloader />}
    <PageHeader>
      <div className="page-title">
        <h1>Portfolio</h1>
      </div>
    </PageHeader>
    <Contents>
      <Table cellSpacing={0} cellPadding={0}>
        <TableHeader>
          <TableRow>
            <TableHeaderItem>Store</TableHeaderItem>
            <TableHeaderItem>Buyer Ident.</TableHeaderItem>
            <TableHeaderItem>Credit card</TableHeaderItem>
            <TableHeaderItem>Description</TableHeaderItem>
            <TableHeaderItem>Amount</TableHeaderItem>
            <TableHeaderItem>Type</TableHeaderItem>
            <TableHeaderItem>Date</TableHeaderItem>
          </TableRow>
        </TableHeader>
        <TableBody>
          <>
            {Object.values(portfolio.transactions || []).map((pf, i) => {
              return <TableRow key={pf.id}>
                <TableColumn>
                  <div className="store">
                    {pf.store.storeName} <span>{pf.store.storeOwner}</span>
                  </div>
                </TableColumn>
                <TableColumn>{pf.buyerIdentification}</TableColumn>
                <TableColumn>{pf.creditCard}</TableColumn>
                <TableColumn>{pf.ttypes.label}</TableColumn>
                <TableColumn>R${parseFloat(pf.value)}</TableColumn>
                <TableColumn>
                  <TypeStatus type={pf.ttypes.type}>
                    {pf.ttypes.type === "in" ? "Entrada" : "Saída"}
                  </TypeStatus>
                </TableColumn>
                <TableColumn>{pf.occurence}</TableColumn>
              </TableRow>
            })}
          </>
        </TableBody>
      </Table>
    </Contents>
  </Container>;
}

const mapStateToProps = (state) => {
  return { ...state.portfolio }
};

export default connect(mapStateToProps, { PortfolioDispatch })(PortfolioViewerPage);