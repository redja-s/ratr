// src/App.js
import React from "react";
import ListFilms from "./components/ListFilms";
import CreateFilm from "./components/CreateFilm";

function App() {
  return (
    <div>
      <CreateFilm />
      <ListFilms />
    </div>
  );
}

export default App;
