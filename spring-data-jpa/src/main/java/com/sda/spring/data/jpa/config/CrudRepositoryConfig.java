package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.domain.Person;
import com.sda.spring.data.jpa.exception.NotFoundException;
import com.sda.spring.data.jpa.repository.PersonCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CrudRepositoryConfig {

    private static final Logger log = LoggerFactory.getLogger(CrudRepositoryConfig.class);

    @Autowired
    public PersonCrudRepository repository;

    @Bean
    public CommandLineRunner repositoryBean() {

        return args -> {
            //create
            repository.save(new Person("Ana", 25));
            repository.save(new Person("Paul", 30));

            //read
            Person paul = repository.findByName("Paul").orElseThrow(() -> new NotFoundException("user not found"));

            Iterable<Person> people = repository.findAll();
            people.forEach(person -> log.info("person: {}", person));
            boolean existsById = repository.existsById(paul.getId());
            log.info("Paul exists by id: {}", existsById);

            //update
            paul.setAge(35);
            repository.save(paul);

            long count = repository.count();
            log.info("count before delete: {}", count);

            //delete
            repository.deleteById(paul.getId());

            repository.deleteAll();

            customFind();
        };
    }

    private void customFind() {
        log.info("--- in custom find");
        repository.save(new Person("Alex", 20));
        Person person = repository.findByName("Alex").orElseThrow(() -> new NotFoundException("user not found"));
        log.info("find person by name 'Alex': {}", person);
    }
}
