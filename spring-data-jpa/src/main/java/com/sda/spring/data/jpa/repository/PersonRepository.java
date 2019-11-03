package com.sda.spring.data.jpa.repository;

import com.sda.spring.data.jpa.domain.Person;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface PersonRepository extends Repository<Person, Long> {
}
