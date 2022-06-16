import React from 'react';
import FileInput from '../components/FileInput';
import "./Main.css";

const Main = () => {
    return (
        <div className='mainContainer'>
            Desafio Full Stack
            <div className='mainBody'>
                <FileInput />
            </div>
        </div>
    )
}

export default Main;