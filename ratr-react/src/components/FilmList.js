// src/components/FilmList.js
import React, { useEffect, useState } from "react";
import axios from "axios";

const FilmList = () => {
  const [films, setFilms] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/films").then((response) => {
      setFilms(response.data);
    });
  }, []);

  return (
    <div>
      <h2>Films</h2>
      <ul>
        {films.map((film) => (
          <li key={film.id}>
            {film.title} (Director: {film.director}, UK Release Date:{" "}
            {film.uk_release_date})
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FilmList;
