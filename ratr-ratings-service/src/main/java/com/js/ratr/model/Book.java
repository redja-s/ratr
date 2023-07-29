package com.js.ratr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bookTitle;
    private String author;
    private String description;

    public Book(String bookTitle, String author, String description) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.description = description;
    }
}
