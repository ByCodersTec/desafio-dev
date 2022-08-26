import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./Pages/Home"
import Transaction from "./Pages/Transaction"
import Import from "./Pages/Import"
import Store from "./Pages/Store"

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/transacao" element={<Transaction />} />
        <Route path="/importacao" element={<Import />} />
        <Route path="/transacao/loja" element={<Store />} />
      </Routes>
    </Router>
  );
}

export default App;
