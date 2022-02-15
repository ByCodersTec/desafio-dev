import { Switch, Route, useRouteMatch, Redirect } from "react-router-dom";

import { Container, Contents } from "./styles";

import Sidebar from "./Sidebar";


const AppPage = () => {

    const { path } = useRouteMatch();

    return <Container>
        <Sidebar />
        <Contents>
            <Switch>
                <Route exact path={path}>
                    <Redirect to={`${path}/stores`} />
                </Route>
                <Route path={`${path}/stores`} />
                <Route path={`${path}/portfolio`} />
                <Route path={`${path}/transactions`} />
            </Switch>
        </Contents>
    </Container>
}

export default AppPage;