package com.sda.spring.boot.rest.controller;

import com.sda.spring.boot.rest.model.Book;
import com.sda.spring.boot.rest.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    //logger
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //CRUD
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        log.info("Get all books...");

        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        log.info("Get book by id: {}", id);

        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/books")
    // the created book type - method name - book details
    public Book createBook(@RequestBody Book book) {
        log.info("Create book: {}", book);

        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    // method name - id - new book details
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        log.info("update book with id: {} and data: {}", id, book);

        //find entity
        Optional<Book> bookOptional = bookRepository.findById(id);

        //set new values
        if (bookOptional.isPresent()) {
            Book savedBook = bookOptional.get();

            // set new values
            savedBook.setTitle(book.getTitle());
            savedBook.setAuthor(book.getAuthor());
            savedBook.setPublished(book.getPublished());

            // save
            Book updatedBook = bookRepository.save(savedBook);

            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        log.info("delete book with id: {}", id);
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete book with id: " + id, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }
}
