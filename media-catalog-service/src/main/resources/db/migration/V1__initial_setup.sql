CREATE TABLE IF NOT EXISTS films (
    id UUID DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    director_name VARCHAR(255) NOT NULL,
    release_year INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    cover_image_path VARCHAR(255),

    PRIMARY KEY (id),
    UNIQUE(title, director_name, release_year)
);

CREATE TABLE IF NOT EXISTS genres (
    id serial NOT NULL,
    genre VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE(genre)
);

CREATE TABLE IF NOT EXISTS films_genres (
    film_id UUID REFERENCES films(id),
    genre_id serial REFERENCES genres(id),

    UNIQUE (film_id, genre_id)
);
