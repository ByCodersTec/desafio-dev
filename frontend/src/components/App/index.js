import { Switch, Route, useRouteMatch, Redirect } from "react-router-dom";

import { Container, Contents } from "./styles";

import Sidebar from "./Sidebar";

import StoresPage from './Pages/Stores';
import PortfolioPage from './Pages/Portfolio';
import PortfolioViewerPage from './Pages/Portfolio/viewer';


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
                <Route path={`${path}/portfolio`} component={PortfolioPage} />
                <Route path={`${path}/portfolio-details/:id`} component={PortfolioViewerPage} />
                <Route path={`${path}/transactions`} />
            </Switch>
        </Contents>
    </Container>
}

export default AppPage;