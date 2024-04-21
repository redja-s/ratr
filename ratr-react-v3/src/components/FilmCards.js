import axios from "axios";
import React, { useEffect, useState } from "react";
import "./table.css";
import defaultFilm from "../assets/reel.svg";

export default function FilmCards() {
  const [films, setFilms] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/films").then((response) => {
      console.log(response.data);
      setFilms(response.data);
    });
  }, []);

  return (
    <div class="album py-5 bg-body-tertiary">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          {films.map((film) => (
            <div class="col">
              <div class="card shadow-sm">
                <img src={defaultFilm} alt="Default" height="10%" width="10%" />
                <div class="card-body">
                  <p class="card-text">{film.description}</p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
