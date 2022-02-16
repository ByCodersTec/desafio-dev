
import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { hasToken } from '../utils/token';

const PublicRoute = ({component, restricted, ...rest}) => {
    const routeComponent = (props) => (
        hasToken() && restricted
            ? <Redirect to={{pathname: '/'}} />
            : React.createElement(component, props)
    );
    return <Route {...rest} render={routeComponent} />;
};

export default PublicRoute;