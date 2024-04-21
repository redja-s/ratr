import axios from "axios";
import React, { useEffect, useState } from "react";
import "./table.css";

export default function ListFilms() {
  const [films, setFilms] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/films").then((response) => {
      console.log(response.data);
      setFilms(response.data);
    });
  }, []);

  return (
    <div>
      <div className="table-container">
        <table className="table table-hover">
          <thead>
            <tr>
              <th scope="col">Title</th>
              <th scope="col">Director</th>
              <th scope="col">Release Year</th>
            </tr>
          </thead>
          <tbody>
            {films.map((film) => (
              <tr key={film.id}>
                <td>{film.title}</td>
                <td>{film.director_name}</td>
                <td>{film.release_year}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
