import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { hasToken } from '../utils/token';

const PrivateRoute = ({component, ...rest}) => {
    const routeComponent = (props) => (
        hasToken()
            ? React.createElement(component, props)
            : <Redirect to={{pathname: '/login'}} />
    );
    return <Route {...rest} render={routeComponent} />;
};

export default PrivateRoute;