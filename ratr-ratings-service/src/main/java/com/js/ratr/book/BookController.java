package com.js.ratr.book;

import com.js.ratr.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/books")
    public Iterable<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book addOneBook(@RequestBody Book book) {
        return this.bookRepository.save(book);
    }

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
