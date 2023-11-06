INSERT INTO genres (genre)
VALUES
    ('Action'),
    ('Adventure'),
    ('Animated'),
    ('Comedy'),
    ('Drama'),
    ('Fantasy'),
    ('Historical'),
    ('Horror'),
    ('Musical'),
    ('Noir'),
    ('Romance'),
    ('Science Fiction'),
    ('Thriller'),
    ('Western')
    ;

INSERT INTO films (title, director_name, release_year, description)
VALUES 
    ('500 Days of Summer', 'Marc Preston Webb', 2009, 'Tom revisits the approximate one year he shared with Summer, the girl he thought he could spend the rest of his life with. She, on the other hand, does not believe in relationships or boyfriends.'),
    ('The Dark Knight', 'Christopher Nolan', 2008, 'Batman has a new foe, the Joker, who is an accomplished criminal hell-bent on decimating Gotham City. Together with Gordon and Harvey Dent, Batman struggles to thwart the Joker before it is too late.'),
    ('Interstellar', 'Christopher Nolan', 2014, 'When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.')
    ;

INSERT INTO films_genres (film_id, genre_id)
VALUES
    ((SELECT id FROM films WHERE title = '500 Days of Summer' AND release_year = 2009),
     (SELECT id FROM genres WHERE genre = 'Romance')),
     ((SELECT id FROM films WHERE title = '500 Days of Summer' AND release_year = 2009),
      (SELECT id FROM genres WHERE genre = 'Comedy'));
