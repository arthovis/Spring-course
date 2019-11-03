package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.book.Book;
import com.sda.spring.data.jpa.book.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Optional;

@Configuration
public class AppConfig {

    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            log.info("In command line runner...");

            Book book = createBook();
            bookRepository.save(book);

            findBookUsingOptionalGet();

            //find using optional orElseThrow
            findBookUsingOptionalOrElseThrow();
        };
    }

    private void findBookUsingOptionalOrElseThrow() {
        Book savedBook = bookRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("Book not found"));
        log.info("saved book: {}", savedBook);
    }

    private void findBookUsingOptionalGet() {
        Optional<Book> savedBookOptional = bookRepository.findById(1L);
        Book savedBook = null;
        if (savedBookOptional.isPresent()) {
            savedBook = savedBookOptional.get();
        }
        log.info("saved book: {}", savedBook);
    }

    private Book createBook() {
        Book book = new Book();
        book.setTitle("Game of Thrones");
        book.setAuthor("George RR Martin");
        book.setPublished(LocalDate.now());
        return book;
    }
}
