import styled from "styled-components";


export const Container = styled.div`
    width: 320px;
    height: 100%;

    display: flex;
    flex-direction: column;

    padding: 25px;

    background-color: var(--primary);
    border-right: 1px solid var(--tertiary);

`;

export const UserContent = styled.div`
    display: flex;
    flex-direction: row;

    align-items: center;

    color: var(--white);

    .user-image {
        width: 50px;
        position: relative;

        img {
            width: 100%;
            height: auto;
            border-radius: 50%;
        }

        span {
            width: 12px;
            height: 12px;

            position: absolute;
            bottom: 3px;
            right: 0;
            
            border: 2px solid var(--primary);
            border-radius: 50%;
            background-color: var(--success);
        }
    }

    .user-block {
        line-height: 14px;
        margin-left: 7px;

        p {
            font-size: 13px;
        }

        span {
            font-size: 11px;
            font-weight: 600;
            color: var(--gray-secondary);
        }
    }

    .user-controls {

    }
`;

export const AppMenu = styled.div`
    width: 100%;
    margin: 25px 0;
`;

export const AppMenuItems = styled.ul`
    display: flex;
    flex-direction: column;
`;

export const AppMenuItem = styled.li`
    a {

        display: flex;
        align-items: center;

        padding: 12px;
        color: var(--white);

        font-size: 14px;

        span {
            color: var(--gray-secondary);
            margin-right: 7px;
        }

        &:hover {
            background-color: var(--active);
            border-radius: 5px;

            span {
                color: var(--white);
            }
        }
        
    }
`;