import { createGlobalStyle } from "styled-components";

const GlobalStyles = createGlobalStyle`
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    ul, li {
        list-style: none;
    }
    a {
        text-decoration: none;
    }
    button, input {
        border: 0;
        outline: 0;
    }
    body, html {
        height: 100%;
    }
    body {
        font-family: 'Roboto', sans-serif;
        font-size: 16px;
        background-color: var(--primary);
        color: var(--white);
    }
    .d-block {
        display: block;
    }
    .d-flex {
        display: flex;
    }
    input, button {
        padding: 12px;
        border-radius: 7px;
    }
    button:hover {
        cursor: pointer;
        opacity: 0.9;
    }
    .remove-button {
        padding: 5px 7px;
        color: var(--white);
        background-color: var(--danger);
    }
    #application {
        height: 100%;
    }
    .error {
        color: var(--danger);
    }
    .shimmer {
        animation : shimmer 2s infinite;
        background: linear-gradient(to right, var(--tertiary) 4%, #3b3e48 25%, var(--tertiary) 36%);
        background-size: 1000px 100%;
    }
    .shimmer-wrapper {
        width: 0px;
        animation: fullView 0.5s forwards linear;
    }
    @keyframes fullView {
        100% {
            width: 100%;
        }
    }
    @keyframes shimmer {
        0% {
            background-position: -1000px 0;
        }
        100% {
            background-position: 1000px 0;
        }
    }
    :root {
        --primary: #1A1B1D;
        --secondary: #141416;
        --tertiary: #2F323C;
        --white: #FFFFFF;
        --black: #000000;
        --success: #BCFE2F;
        --danger: #FF4C00;
        --grey-primary: #2D2F32;
        --gray-secondary: #707277;
        --grey-tertiary: #B7B7B7;
        --active: #4422EF;
    }
`;

export default GlobalStyles;