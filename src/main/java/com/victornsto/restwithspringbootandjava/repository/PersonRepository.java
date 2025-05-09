package com.victornsto.restwithspringbootandjava.repository;

import com.victornsto.restwithspringbootandjava.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Page<Person> findByfirstNameContainingIgnoreCase(String firstName, Pageable pageable);
}
