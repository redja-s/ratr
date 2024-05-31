import React from 'react';
import Navbar from "./Navbar.jsx";
import { useEffect, useState } from 'react';
import axios from 'axios';

const ListFilms = () => {
  const [films, setFilms] = useState([])
  useEffect(() => {
    axios.get("http://localhost:8080/films")
      .then((res) => {
        setFilms(res.data);
      })
  }, [])
  console.log(films);
  // console.log(films[0].title);
  return (
    <div>
      <Navbar />
      <ul>
        {
          films.map((film) => (
            <li key={film.id}>
              <h2>{film.title}</h2>
            </li>
          ))
        }
      </ul>
    </div>
  );
}

export default ListFilms;
