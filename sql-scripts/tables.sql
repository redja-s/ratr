CREATE TABLE IF NOT EXISTS films (
    id uuid,
    title VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    genre genre_enum NOT NULL,
    director uuid NOT NULL,
    director_name VARCHAR NOT NULL,

)