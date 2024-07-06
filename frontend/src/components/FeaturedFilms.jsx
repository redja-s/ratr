import axios from "axios";
import React from "react";
import NoImage from "../assets/no-image.png";
import "../css/FeaturedFilms.css";

const FeaturedFilms = () => {
  const numOfFeaturedFilms = 6;

  const createFilmCard = function card(listOfFilms) {
    return (
      <div className="text-center items-center">
        <ul className="flex justify-content-between">
          {listOfFilms.map((film) => (
            <li key={film.id} className="flex-1 w-1/5">
              {film.cover_image_path == null ? (
                <img
                  className="featured-films-list-item"
                  src={NoImage}
                  alt={film.title}
                />
              ) : (
                <img
                  className="featured-films-list-item"
                  src={film.cover_image_path}
                  alt={film.title}
                />
              )}
            </li>
          ))}
        </ul>
      </div>
    );
  };

  const [featuredFilms, setFeaturedFilms] = React.useState([]);
  React.useEffect(() => {
    axios
      .get("http://localhost:8080/films/" + numOfFeaturedFilms)
      .then((res) => {
        setFeaturedFilms(res.data);
      });
  }, []);
  console.log(featuredFilms);

  return (
    <div className="justify-center flex items-center">
      {createFilmCard(featuredFilms, numOfFeaturedFilms)}
    </div>
  );
};

export default FeaturedFilms;
