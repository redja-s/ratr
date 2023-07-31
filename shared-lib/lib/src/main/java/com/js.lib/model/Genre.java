package com.js.lib.model;

public enum Genre {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    THRILLER("Thriller"),
    ;

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
