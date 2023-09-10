package com.victornsto.restwithspringbootandjava;

import com.victornsto.restwithspringbootandjava.data.vo.v1.PersonVo;
import com.victornsto.restwithspringbootandjava.services.PersonServices;
import com.victornsto.restwithspringbootandjava.v2.PersonVoV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVo findByID(
            @PathVariable(value = "id") Long id
    ) throws Exception {

        return personServices.findById(id);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVo> findAll() {
        return personServices.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVo create(@RequestBody PersonVo person){
        return personServices.create(person);
    }
    @PostMapping(value = "/v2",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVoV2 createV2(@RequestBody PersonVoV2 person){
        return personServices.createV2(person);
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PersonVo update(@RequestBody PersonVo person){
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        personServices.delete(id);
    }
}
