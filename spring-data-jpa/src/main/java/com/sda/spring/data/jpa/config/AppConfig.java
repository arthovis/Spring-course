package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.book.Book;
import com.sda.spring.data.jpa.book.BookRepository;
import com.sda.spring.data.jpa.association.Father;
import com.sda.spring.data.jpa.association.Son;
import com.sda.spring.data.jpa.exception.NotFoundException;
import com.sda.spring.data.jpa.association.FatherRepository;
import com.sda.spring.data.jpa.association.SonRepository;
import com.sda.spring.data.jpa.validation.User;
import com.sda.spring.data.jpa.validation.UserRepository;
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

    @Autowired
    private FatherRepository fatherRepository;

    @Autowired
    private SonRepository sonRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            log.info("In command line runner...");

            Book book = createBook();
            bookRepository.save(book);

            findBookUsingOptionalGet();

            findBookUsingOptionalOrElseThrow();

            testAssociations();

            testValidation();

        };
    }

    private void testValidation() {
        //User user = createUser();
        User user = createBadUser();
        userRepository.save(user);
        User savedUser = userRepository.findById(5L).orElseThrow(() -> new NotFoundException("User not found"));
        log.info("saved user: {}", savedUser);
    }

    private User createUser() {
        User user = new User();

        user.setName("Jon Snow");
        user.setEmail("jon.snow@gmail.com");
        user.setAge(30);
        user.setConsent(true);
        user.setAboutMe("I know nothing");
        return user;
    }

    private User createBadUser() {
        User user = new User();

        user.setName("");
        user.setEmail("jon.snowgmail.com");
        user.setAge(5);
        user.setConsent(false);
        user.setAboutMe("I know nothing");
        return user;
    }

    private void testAssociations() {
        Son son1 = new Son();
        son1.setName("son1");

        Son son2 = new Son();
        son2.setName("son2");

        Father father = new Father();
        father.setName("father");
        father.getSons().add(son1);
        father.getSons().add(son2);
        fatherRepository.save(father);
        log.info("father repository size: {}", fatherRepository.count());
        log.info("son repository size: {}", sonRepository.count());
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
