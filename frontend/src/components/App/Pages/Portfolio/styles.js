import styled from "styled-components";

const handleStatusBackground = (status) => {
    switch (status) {
        case 'pending':
            return "background: var(--gray-secondary); color: var(--white)";
        case 'processing':
            return "background: var(--active); color: var(--white)";
        case 'error':
            return "background: var(--danger); color: var(--white)";
        default:
            return "background: var(--success); color: var(--primary)";
    }
}

export const PortfolioStatus = styled.span`
    padding: 4px 8px;
    border-radius: 5px;
    font-weight: 600;
    font-size: 11px;
    ${({ status }) => handleStatusBackground(status)};
`;

const handleTypeBackground = (status) => {
    switch (status) {
        case 'out':
            return "background: var(--danger); color: var(--white)";
        case 'in':
            return "background: var(--success); color: var(--primary)";
        default:
            return "background: var(--gray-secondary); color: var(--white)";
    }
}

export const TypeStatus = styled.span`
    padding: 4px 8px;
    border-radius: 5px;
    font-weight: 600;
    font-size: 11px;
    ${({ type }) => handleTypeBackground(type)};
`;