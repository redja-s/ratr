import React from 'react';

const FilmView = ({ match }) => {
    const filmId = match.params.id;

    return (
        <div>
            <h2>filmId</h2>
        </div>
    )
}

export default FilmView;