import React, { useEffect, useState } from "react";
import axios from "axios";

const ListFilms = () => {
  const [films, setFilms] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/films").then((response) => {
      setFilms(response.data);
    });
  }, []);

  return (
    <div class="h-56 content-center">
      <table class="table-auto border-separate border-spacing-2">
        <thead>
          <tr>
            <th>Title</th>
            <th>Director</th>
            <th>Release Year</th>
          </tr>
        </thead>
        <tbody>
          {
            films.map((film) => (
                <tr key={film.id}>
                    <td> {film.title} </td>
                    <td> {film.director_name} </td>
                    <td> {film.release_year} </td>
                </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
};

export default ListFilms;
