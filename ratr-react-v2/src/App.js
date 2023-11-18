import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from "./components/Header.js";

import Home from './pages/Home.js'
import Films from './pages/Films.js'
import Error from './pages/Error.js'


function App() {
  return (
    <Router>
        <Header />
        <Routes>
            <Route exact path='/' exact component={Home}/>
            <Route exact path='/films' exact component={Films}/>

        </Routes>
    </Router>
  );
}

export default App;
