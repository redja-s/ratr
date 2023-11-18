import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const FilmTable = () => {
  const [films, setFilms] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/films").then((response) => {
        console.log(response.data)
      setFilms(response.data);
    });
  }, []);

    return (
      <table className="table table-hover">
        <thead>
          <tr>
            <th scope="col">Title</th>
            <th scope="col">Director</th>
            <th scope="col">Release Year</th>
          </tr>
        </thead>
        <tbody>
          {
            films.map((film) => (
                <tr key={film.id}>
                    <td scope="row">
                        <Link to={`/film/${film.id}`}>{film.title} </Link>
                    </td>
                    <td> {film.director_name} </td>
                    <td> {film.release_year} </td>
                </tr>
            ))
          }
        </tbody>
      </table>
    )
}

export default FilmTable;
