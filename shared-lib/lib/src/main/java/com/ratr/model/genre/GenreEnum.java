package com.ratr.model.genre;

public enum GenreEnum {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATED("Animated"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HISTORICAL("Historical"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    NOIR("Noir"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    THRILLER("Thriller"),
    WESTERN("Western"),
    ;

    private final String genreAsString;

    GenreEnum(String genreAsString) {
        this.genreAsString = genreAsString;
    }

    public String toString() {
        return genreAsString;
    }
}
