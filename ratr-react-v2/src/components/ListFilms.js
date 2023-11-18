import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import FilmTable from "./FilmTable";
import FilmView from "./FilmView";


const ListFilms = () => {

  return (
    <Router>
        <Routes>
            <Route path="/" exact component={FilmTable} />
            <Route path="/film/:id" component={FilmView} />
        </Routes>
    </Router>
  );
};

export default ListFilms;
