import React from 'react';
import './styles.css'
import { Link } from 'react-router-dom'

const Navigation = () => {
    return (
        <nav className='navbar'>

            <div className='navbar_left'>
                <Link to="/"> Home </Link>
                <Link to="/transacao"> Transações </Link>
                <Link to="/transacao/loja"> Transações por Lojas </Link>
                <Link to="/importacao"> Importar </Link>
            </div>

            <div className='navbar_right'>
                <p>Bem vindo</p>
            </div>

        </nav>
    )
}

export default Navigation;