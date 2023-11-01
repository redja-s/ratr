CREATE TABLE IF NOT EXISTS films (
    id UUID DEFAULT gen_random_uuid(),
    title VARCHAR NOT NULL,
    director_name VARCHAR NOT NULL,
    release_year INT NOT NULL,

    PRIMARY KEY (id),
    UNIQUE(title, director_name, release_year)
);

INSERT INTO films (title, director_name, release_year)
VALUES 
    ('500 Days of Summer', 'Marc Preston Webb', 2009),
    ('The Dark Knight', 'Christopher Nolan', 2008),
    ('Interstellar', 'Christopher Nolan', 2014),
    ('Inception', 'Christopher Nolan', 2010);
