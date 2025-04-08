package com.victornsto.restwithspringbootandjava.service;

import com.victornsto.restwithspringbootandjava.dto.PersonDto;
import com.victornsto.restwithspringbootandjava.exceptions.ResourceNotFoudException;
import com.victornsto.restwithspringbootandjava.model.Person;
import com.victornsto.restwithspringbootandjava.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PersonServices {
    final private PersonRepository personRepository;
    final private ConversionService conversionService;
    public PersonServices(PersonRepository personRepository, ConversionService conversionService) {
        this.personRepository = personRepository;
        this.conversionService = conversionService;
    }
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public List<PersonDto> findAll() {
        logger.info("Finding all people!");
        return personRepository.findAll()
                .stream()
                .map(person -> conversionService.convert(person, PersonDto.class))
                .collect(Collectors.toList());
    }

    public PersonDto findById(String id) {
        logger.info("Finding one person!");
        return  conversionService.convert(
                this.findByIdRepository(Long.valueOf(id)),
                PersonDto.class
        );
    }

    public Person findByIdRepository(Long id) {
        logger.info("Finding one person!");
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoudException("Person not found!"));
    }

    public PersonDto create(Person person) {
        logger.info("Creating one person!");
        Person result = new Person();
        if (person.getId() == null) {
            result = personRepository.save(person);

        }
        return conversionService.convert(result, PersonDto.class);
    }

    public PersonDto update(Person personDto) {
        logger.info("Updating one person!");
        Person result = this.findByIdRepository(personDto.getId());
        result.setAddress(personDto.getAddress());
        result.setFirstName(personDto.getFirstName());
        result.setLastName(personDto.getLastName());
        result.setGender(personDto.getGender());
        return conversionService.convert(personRepository.save(result), PersonDto.class);
    }

    public void delete(String id) {
        logger.info("Deleting one person!");
        Person result = this.findByIdRepository(Long.valueOf(id));
        personRepository.delete(result);
    }

}
