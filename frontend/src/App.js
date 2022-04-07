import React from 'react';
import GlobalStyle from './styles/global';
import 'bootstrap/dist/css/bootstrap.min.css';

import Routes from './routes';

function App() {
  return (
    <>
      <Routes />
      <GlobalStyle />
    </>
  );
}

export default App;
