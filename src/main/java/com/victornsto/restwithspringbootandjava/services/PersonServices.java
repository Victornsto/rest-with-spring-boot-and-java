package com.victornsto.restwithspringbootandjava.services;

import com.victornsto.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.victornsto.restwithspringbootandjava.model.Person;
import com.victornsto.restwithspringbootandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person findById(Long id) {

        logger.info("Finding one person");

        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public List<Person> findAll() {
        logger.info("Finding all peoples");

        return personRepository.findAll();
    }

    public Person create(Person person){
        logger.info("Creating people");
        return personRepository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating people");

        Optional<Person> entity = personRepository.findById(person.getId());
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException("No records found for this ID!");
        }

        entity.get().setFirtName(person.getFirtName());
        entity.get().setLastName(person.getLastName());
        entity.get().setAddress(person.getAddress());
        entity.get().setGender(person.getGender());

        return personRepository.save(entity.get());
    }

    public void delete(Long id) {
        logger.info("Deleting person");
        Optional<Person> entity = personRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException("No records found for this ID!");
        }
        personRepository.delete(entity.get());
    }

}
