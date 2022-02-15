import styled from "styled-components";

export const Container = styled.div`
    width: 100%;
    height: 100%;

    display: flex;
    flex-direction: column;
`;

export const PageHeader = styled.div`
    width: 100%;

    display: flex;
    flex-direction: row;

    align-items: center;

    justify-content: space-between;

    padding: 25px;
    border-bottom: 1px solid var(--tertiary);

    .page-title {

    }
`;

export const Contents = styled.div`
    width: 100%;

    display: flex;
`;