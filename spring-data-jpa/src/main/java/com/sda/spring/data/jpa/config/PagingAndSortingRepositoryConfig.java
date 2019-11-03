package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.domain.Person;
import com.sda.spring.data.jpa.exception.NotFoundException;
import com.sda.spring.data.jpa.repository.PersonCrudRepository;
import com.sda.spring.data.jpa.repository.PersonPagingAndSortingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Configuration
public class PagingAndSortingRepositoryConfig {

    private static final Logger log = LoggerFactory.getLogger(PagingAndSortingRepositoryConfig.class);

    @Autowired
    public PersonPagingAndSortingRepository repository;

    @Bean
    public CommandLineRunner repositoryBean() {

        return args -> {
            loadData();

            paginate();

            sort();

            paginateAndSort();
        };
    }

    private void paginateAndSort() {
        Pageable sortedByName = PageRequest.of(0, 5, Sort.by("name"));
        Pageable sortedByAgeDescending = PageRequest.of(0, 5, Sort.by("age").descending());
        Pageable sortedByAgeDescendingAndNameAscending = PageRequest.of(0, 5, Sort.by("age").descending().and(Sort.by("name")));

        Page<Person> peopleSortedByName = repository.findAll(sortedByName);
        Page<Person> peopleSortedByAgeDescending = repository.findAll(sortedByAgeDescending);
        Page<Person> peopleSortedByAgeDescendingAndNameAscending = repository.findAll(sortedByAgeDescendingAndNameAscending);
    }

    private void sort() {
        Sort sortByName = Sort.by("name");
        Iterable<Person> sortedPeople = repository.findAll(sortByName);
        sortedPeople.forEach(person -> log.info("person: {}", person));
    }

    private void paginate() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Pageable secondPageWithOneElement = PageRequest.of(1, 1);

        Page<Person> allPeople = repository.findAll(firstPageWithTwoElements);
        Page<Person> allPeopleAge32 = repository.findAllByAge(32, firstPageWithTwoElements);
    }

    private void loadData() {
        repository.save(new Person("paul", 32));
        repository.save(new Person("alina", 28));
        repository.save(new Person("kazimir", 34));
        repository.save(new Person("lidia", 32));
        repository.save(new Person("violeta", 32));
    }

}
