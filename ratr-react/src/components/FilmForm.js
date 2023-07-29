// src/components/FilmForm.js
import React, { useState } from "react";
import axios from "axios";

const FilmForm = () => {
  const [title, setTitle] = useState("");
  const [director, setDirector] = useState("");
  const [ukReleaseDate, setUkReleaseDate] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const newFilm = {
      title,
      director,
      uk_release_date: ukReleaseDate,
    };

    axios.post("http://localhost:8080/films", newFilm).then((response) => {
      console.log("Film added successfully:", response.data);
    });
  };

  return (
    <div className="p-4 bg-white rounded-lg shadow-lg">
      <h2 className="text-2xl font-bold mb-4">Add Film</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block text-gray-700 text-sm font-bold mb-2">Title:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="w-full px-3 py-2 border rounded-lg shadow-sm focus:outline-none focus:border-blue-300"
          />
        </div>
        <div>
          <label className="block text-gray-700 text-sm font-bold mb-2">Director:</label>
          <input
            type="text"
            value={director}
            onChange={(e) => setDirector(e.target.value)}
            className="w-full px-3 py-2 border rounded-lg shadow-sm focus:outline-none focus:border-blue-300"
          />
        </div>
        <div>
          <label className="block text-gray-700 text-sm font-bold mb-2">UK Release Date:</label>
          <input
            type="text"
            value={ukReleaseDate}
            onChange={(e) => setUkReleaseDate(e.target.value)}
            className="w-full px-3 py-2 border rounded-lg shadow-sm focus:outline-none focus:border-blue-300"
          />
        </div>
        <div>
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600"
          >
            Add Film
          </button>
        </div>
      </form>
    </div>
  );
};

export default FilmForm;
