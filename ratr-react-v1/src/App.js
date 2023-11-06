// src/App.js
import React from "react";
import ListFilms from "./components/ListFilms";
import CreateFilm from "./components/CreateFilm";
import './App.css';

function App() {
  return (
    <div>
        <CreateFilm />
        <ListFilms />
    </div>
  );
}

export default App;
