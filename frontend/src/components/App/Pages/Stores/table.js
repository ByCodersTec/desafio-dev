import React from 'react';
import styled from "styled-components";


export const Table = styled.table`
    width: 100%;
    height: 100%;

    border: none;

    thead {
        color: var(--tertiary);
        font-size: 11px;

        height: 55px;

        font-weight: normal;
        text-transform: uppercase;

        text-align: left;

        th {
            padding: 5px 35px;
        }
    } 

    tbody {
        color: var(--white);
        
        font-size: 12px;

        td {
            padding: 20px 35px;

            .up {
                color: var(--success);
                font-weight: bold;

                &::before {
                    content: "+";
                }
            }

            .down {
                color: var(--danger);
                font-weight: bold;
            }
        }

        tr:nth-of-type(odd) {
            background-color: var(--primary);
        }
    }
`;

export const TableHeader = ({ children }) => {
    return <thead>
        {children}
    </thead>
}

export const TableRow = styled.tr`
    td.with-icon {
        display: flex;
        align-items: center;

        img {
            margin-right: 5px;
        }
    }
`;

export const TableColumn = styled.td`

`;

export const TableHeaderItem = styled.th`

`;

export const TableBody = ({ children }) => {
    return <tbody>
        {children}
    </tbody>
}

export const ActiveIcon = styled.img`
    width: 20px;
    height: 20px;
`;