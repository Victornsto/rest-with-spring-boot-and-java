package com.victornsto.restwithspringbootandjava.controller;

import com.victornsto.restwithspringbootandjava.dto.v1.PersonDto;
import com.victornsto.restwithspringbootandjava.dto.v2.v1.PersonDtoV2;
import com.victornsto.restwithspringbootandjava.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping
    public List<PersonDto> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public PersonDto findById(@PathVariable(value = "id") String id) {
        return service.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto create(@RequestBody PersonDto personDto) {
        return service.create(personDto);
    }

    @PostMapping(
            value = "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDtoV2 createV2(@RequestBody PersonDtoV2 person) {
        return service.createV2(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto update(@RequestBody PersonDto person) {
        return service.update(person);
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        service.delete(id);
    }
}
