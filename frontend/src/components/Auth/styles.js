import styled from 'styled-components';

export const Container = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 25px;
`; 

export const LoginFormContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 350px;
    @media (max-width: 750px) {
        width: 100%;
    }
    height: 100%;
    form {
        width: 100%;
    }
`;

export const FormGroup = styled.div`
    display: flex;
    flex-direction: column;
    margin-bottom: 15px;
    label {
        font-size: 14px;
        padding: 5px 0;
    }
    input {
        background-color: var(--grey-primary);
        color: var(--white);
    }
`;

export const FormSubmitButton = styled.button`
    width: 100%;
    
    margin-top: 15px;
    background-color: var(--active);
    color: var(--white);
`;