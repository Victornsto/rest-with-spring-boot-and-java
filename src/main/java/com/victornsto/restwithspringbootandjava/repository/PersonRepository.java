package com.victornsto.restwithspringbootandjava.repository;

import com.victornsto.restwithspringbootandjava.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
