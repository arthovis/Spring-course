package com.sda.spring.data.jpa.repository;

import com.sda.spring.data.jpa.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonJpaRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);

}
