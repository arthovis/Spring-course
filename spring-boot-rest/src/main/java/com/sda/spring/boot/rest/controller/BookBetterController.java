package com.sda.spring.boot.rest.controller;

import com.sda.spring.boot.rest.dto.BookRequest;
import com.sda.spring.boot.rest.dto.BookResponse;
import com.sda.spring.boot.rest.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.sda.spring.boot.rest.controller.BookBetterController.API_BOOKS;

@RequestMapping(API_BOOKS)
@RestController
public class BookBetterController {
    public static final String API_BOOKS = "/api/books";
    //logger
    private static final Logger log = LoggerFactory.getLogger(BookBetterController.class);
    private BookService bookService;

    @Autowired
    public BookBetterController(BookService bookRepository) {
        this.bookService = bookRepository;
    }

    //CRUD
    @GetMapping
    public List<BookResponse> getAllBooks() {
        log.info("Get all books...");

        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("id") Long id) {
        log.info("Get book by id: {}", id);

        BookResponse bookResponse = bookService.findById(id);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping
    // the created book type - method name - book details
    public BookResponse createBook(@RequestBody BookRequest bookRequest) {
        log.info("Create book: {}", bookRequest);

        return bookService.save(bookRequest);
    }

    @PutMapping("/{id}")
    // method name - id - new book details
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") Long id, @RequestBody BookRequest bookRequest) {
        log.info("update book with id: {} and data: {}", id, bookRequest);

        BookResponse updatedBook = bookService.update(bookRequest, id);

        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> partialUpdateBook(@PathVariable("id") Long id, @RequestBody Map<String, Object> updateRequest) {
        log.info("update book with id: {} and data: {}", id, updateRequest);

        BookResponse updatedBook = bookService.partialUpdate(updateRequest, id);

        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        log.info("delete book with id: {}", id);
        try {
            bookService.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete book with id: " + id, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }
}
