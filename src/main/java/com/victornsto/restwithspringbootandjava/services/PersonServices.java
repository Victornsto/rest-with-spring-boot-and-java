package com.victornsto.restwithspringbootandjava.services;

import com.victornsto.restwithspringbootandjava.controller.PersonController;
import com.victornsto.restwithspringbootandjava.dto.v1.PersonDto;
import com.victornsto.restwithspringbootandjava.exceptions.ResourceNotFoudException;
import com.victornsto.restwithspringbootandjava.model.Person;
import com.victornsto.restwithspringbootandjava.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    public Page<PersonDto> findAll(Pageable pageable) {
        logger.info("Finding all people!");
        return personRepository.findAll(pageable)
                                .map(person -> {
                                    PersonDto dto = conversionService.convert(person, PersonDto.class);
                                    assert dto != null;
                                    addHateoasLinks(dto);
                                    return dto;
                                });
    }

    public Page<PersonDto> findAllByName(String firstName ,Pageable pageable) {
        logger.info("Finding all people!");
        return personRepository.findByfirstNameContainingIgnoreCase(firstName ,pageable)
                .map(person -> {
                    PersonDto dto = conversionService.convert(person, PersonDto.class);
                    assert dto != null;
                    addHateoasLinks(dto);
                    return dto;
                });
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

    public PersonDto update(PersonDto personDto) {
        logger.info("Updating one person!");
        Person result = this.findByIdRepository(personDto.getId());
        result.setAddress(personDto.getAddress());
        result.setFirstName(personDto.getFirstName());
        result.setLastName(personDto.getLastName());
        result.setGender(personDto.getGender());
        result.setEnabled(personDto.getEnabled());
        result.setBirthDay(personDto.getBirthDay());
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
        dto.add(linkTo(methodOn(PersonController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(String.valueOf(dto.getId()))).withRel("delete").withType("DELETE"));
    }

}
