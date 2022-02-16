import { connect } from 'react-redux';

import { Link, useRouteMatch } from "react-router-dom";
import { AppMenu, AppMenuItem, AppMenuItems, Container, UserContent } from "./styles";

import { Dispatch as UserDispatch } from '../../../store/modules/user/actions';
import { useEffect } from 'react';
import UserContentShimmer from '../Shimmers/UserContentShimmer';

const Sidebar = ({ loading, data, error, UserDispatch }) => {

    const { path } = useRouteMatch();

    const MenuItems = [
        { "title": "Stores", "icon": "fas fa-th-large", "route": "/stores" },
        { "title": "Portfolio", "icon": "fas fa-th-large", "route": "/portfolio" },
        { "title": "Transactions", "icon": "fas fa-shopping-bag", "route": "/transactions" },
    ];

    useEffect(() => {
        UserDispatch()
    }, [UserDispatch])

    return <Container>
        <UserContent>
            {loading ? (
                <UserContentShimmer />
            ) : (
                <>
                    <div className="user-image">
                        <img src={data.avatar} alt="Pic" />
                        <span></span>
                    </div>
                    <div className="user-block">
                        <p>{`${data.first_name} ${data.last_name}` || ""}</p>
                        <span>{data.username || ""}</span>
                    </div>
                    <div className="user-controls"></div>
                </>
            )}
        </UserContent>
        <AppMenu>
            <AppMenuItems>
                {MenuItems.map((item, i) => {
                    return <AppMenuItem key={i}>
                        <Link to={`${path}${item.route}`}><span className={item.icon} />{item.title}</Link>
                    </AppMenuItem>
                })}
            </AppMenuItems>
        </AppMenu>
    </Container>
}

const mapStateToProps = (state) => {
    return { ...state.user }
};

export default connect(mapStateToProps, { UserDispatch })(Sidebar);
