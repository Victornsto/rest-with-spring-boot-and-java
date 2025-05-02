package com.victornsto.restwithspringbootandjava.controller;

import com.victornsto.restwithspringbootandjava.controller.docs.PersonControllerDocs;
import com.victornsto.restwithspringbootandjava.dto.v1.PersonDto;
import com.victornsto.restwithspringbootandjava.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Tag(name = "Person", description = "Endpoints for managing persons")
public class PersonController implements PersonControllerDocs {

    private final PersonServices service;

    public PersonController(PersonServices service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public List<PersonDto> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public PersonDto findById(@PathVariable(value = "id") String id) {
        return service.findById(id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public PersonDto create(@RequestBody PersonDto personDto) {
        return service.create(personDto);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public PersonDto update(@RequestBody PersonDto person) {
        return service.update(person);
    }


    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
