package com.victornsto.restwithspringbootandjava.unittests.service;

import com.victornsto.restwithspringbootandjava.controller.PersonController;
import com.victornsto.restwithspringbootandjava.dto.v1.PersonDto;
import com.victornsto.restwithspringbootandjava.dto.v2.v1.PersonDtoV2;
import com.victornsto.restwithspringbootandjava.exceptions.ResourceNotFoudException;
import com.victornsto.restwithspringbootandjava.model.Person;
import com.victornsto.restwithspringbootandjava.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServices {
    final private PersonRepository personRepository;
    final private ConversionService conversionService;
    public PersonServices(PersonRepository personRepository, ConversionService conversionService) {
        this.personRepository = personRepository;
        this.conversionService = conversionService;
    }
    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public List<PersonDto> findAll() {
        logger.info("Finding all people!");
        var dto = personRepository.findAll()
                .stream()
                .map(person -> conversionService.convert(person, PersonDto.class))
                .toList();
        dto.forEach(PersonServices::addHateoasLinks);
        return dto;
    }

    public PersonDto findById(String id) {
        logger.info("Finding one person!");
        var dto = conversionService.convert(
                this.findByIdRepository(Long.valueOf(id)),
                PersonDto.class);
        assert dto != null;
        addHateoasLinks(dto);
        return dto;
    }

    public Person findByIdRepository(Long id) {
        logger.info("Finding one person!");
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoudException("Person not found!"));
    }

    public PersonDto create(PersonDto personDto) {
        logger.info("Creating one person!");
        Person result = new Person();
        if (personDto.getId() == null) {
            result = personRepository.save(Objects.requireNonNull(conversionService.convert(personDto, Person.class)));

        }
        var dto = conversionService.convert(result, PersonDto.class);
        assert dto != null;
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDtoV2 createV2(PersonDtoV2 person) {
        logger.info("Creating one person!");
        Person result = new Person();
        if (person.getId() == null) {
            result = personRepository.save(Objects.requireNonNull(conversionService.convert(person, Person.class)));

        }
        return conversionService.convert(result, PersonDtoV2.class);
    }

    public PersonDto update(PersonDto personDto) {
        logger.info("Updating one person!");
        Person result = this.findByIdRepository(personDto.getId());
        result.setAddress(personDto.getAddress());
        result.setFirstName(personDto.getFirstName());
        result.setLastName(personDto.getLastName());
        result.setGender(personDto.getGender());
        var dto = conversionService.convert(personRepository.save(result), PersonDto.class);
        assert dto != null;
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(String id) {
        logger.info("Deleting one person!");
        Person result = this.findByIdRepository(Long.valueOf(id));
        var dto = conversionService.convert(result, PersonDto.class);
        personRepository.delete(result);
        assert dto != null;
        addHateoasLinks(dto);
    }

    private static void addHateoasLinks(PersonDto dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(String.valueOf(dto.getId()))).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(String.valueOf(dto.getId()))).withRel("delete").withType("DELETE"));
    }

}
