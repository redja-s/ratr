import logo from './logo.svg';
import './App.css';
import ListFilms from "./components/ListFilms.js";
import Header from "./components/Header.js";

function App() {
  return (
    <div className = "mt-5 container-fluid">
          <Header />
      <div className = "card">

        <div className = "card-header">
        List of Films
        </div>
        <ListFilms />
      </div>
    </div>
  );
}

export default App;
