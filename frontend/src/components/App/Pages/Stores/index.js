import { useEffect } from "react";
import { connect } from 'react-redux';

import { Table, TableHeader, TableHeaderItem, TableBody, TableRow, TableColumn, ActiveIcon } from "./table";
import { Container, PageHeader, Contents } from '../styles';

import { RefreshButton } from "./styles"

import Preloader from "../../../Preloader";

import { Dispatch as StoresDispatch } from "../../../../store/modules/stores/actions";

const StoresPage = ({loading, stores, StoresDispatch}) => {

    useEffect(() => {
        StoresDispatch();
    }, [StoresDispatch]);

    return <Container>
        {loading && <Preloader />}
        <PageHeader>
            <div className="page-title">
                <h1>Stores</h1>
            </div>
            <div className="page-actions">
                <RefreshButton type="button" className="" onClick={StoresDispatch}>Refresh</RefreshButton>
            </div>
        </PageHeader>
        <Contents>
            <Table cellSpacing={0} cellPadding={0}>
                <TableHeader>
                    <TableRow>
                        <TableHeaderItem>Id</TableHeaderItem>
                        <TableHeaderItem>Store Name</TableHeaderItem>
                        <TableHeaderItem>Store Owner</TableHeaderItem>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    <>
                        {Object.values(stores).map((store, i) => {
                            console.log(store)
                            return <TableRow key={i}>
                                <TableColumn>{store.id}</TableColumn>
                                <TableColumn>{store.storeName}</TableColumn>
                                <TableColumn>{store.storeOwner}</TableColumn>
                            </TableRow>
                        })}
                    </>
                </TableBody>
            </Table>
        </Contents>
    </Container>;
}

const mapStateToProps = (state) => {
    return { ...state.stores }
};
  
export default connect(mapStateToProps, { StoresDispatch })(StoresPage);