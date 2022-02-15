import { Switch, Route, useRouteMatch, Redirect } from "react-router-dom";

import { Container, Contents } from "./styles";

import Sidebar from "./Sidebar";

import StoresPage from './Pages/Stores';


const AppPage = () => {

    const { path } = useRouteMatch();

    return <Container>
        <Sidebar />
        <Contents>
            <Switch>
                <Route exact path={path}>
                    <Redirect to={`${path}/stores`} />
                </Route>
                <Route path={`${path}/stores`} component={StoresPage} />
                <Route path={`${path}/portfolio`} />
                <Route path={`${path}/transactions`} />
            </Switch>
        </Contents>
    </Container>
}

export default AppPage;