import styled from "styled-components";

export const Container = styled.div`
    position: fixed;
    top: 0;
    left: 0;

    text-align: center;

    width: 100%;
    height: 100%;

    background-color: rgba(47, 50, 60, 0.95);
`;

export const PreloaderContent = styled.div`
    display: inline-block;
    width: 30px;
    height: 30px;
    position: relative;
    border: 4px solid #Fff;
    top: 50%;
    animation: loader 2s infinite ease;

    @keyframes loader {
        0% {
            transform: rotate(0deg);
        }
        
        25% {
            transform: rotate(180deg);
        }
        
        50% {
            transform: rotate(180deg);
        }
        
        75% {
            transform: rotate(360deg);
        }
        
        100% {
            transform: rotate(360deg);
        }
    }
`;

export const PreloaderContentInner = styled.div`
    vertical-align: top;
    display: inline-block;
    width: 100%;
    background-color: #fff;
    animation: loader-inner 2s infinite ease-in;

    @keyframes loader-inner {
        0% {
            height: 0%;
        }
        
        25% {
            height: 0%;
        }
        
        50% {
            height: 100%;
        }
        
        75% {
            height: 100%;
        }
        
        100% {
            height: 0%;
        }
    }
`;