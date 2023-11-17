import React, { useEffect, useState } from "react";
import axios from "axios";
import FilmTable from "./FilmTable.js";

const ListFilms = () => {

  return (
    <div className="card-body">
        <FilmTable />
    </div>
  );
};

export default ListFilms;
