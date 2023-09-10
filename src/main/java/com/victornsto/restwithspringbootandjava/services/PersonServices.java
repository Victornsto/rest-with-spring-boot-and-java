package com.victornsto.restwithspringbootandjava.services;

import com.victornsto.restwithspringbootandjava.data.vo.v1.PersonVo;
import com.victornsto.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.victornsto.restwithspringbootandjava.mapper.DozerMapper;
import com.victornsto.restwithspringbootandjava.mapper.custom.PersonMapper;
import com.victornsto.restwithspringbootandjava.model.Person;
import com.victornsto.restwithspringbootandjava.repositories.PersonRepository;
import com.victornsto.restwithspringbootandjava.v2.PersonVoV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapperService;

    public PersonVo findById(Long id) {

        logger.info("Finding one person");

        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()){
            new ResourceNotFoundException("Not found a person for this ID");
        }

        return DozerMapper.parseObject(person.get(), PersonVo.class);
    }

    public List<PersonVo> findAll() {
        logger.info("Finding all peoples");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVo.class);
    }

    public PersonVo create(PersonVo person){
        logger.info("Creating people");
        var entiny = DozerMapper.parseObject(person, Person.class);
        var vo =  DozerMapper.parseObject(personRepository.save(entiny), PersonVo.class);
        return vo;
    }

    public PersonVo update(PersonVo person){
        logger.info("Updating people");

        Optional<Person> entity = personRepository.findById(person.getId());
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException("No records found for this ID!");
        }

        entity.get().setfirstName(person.getFirstName());
        entity.get().setLastName(person.getLastName());
        entity.get().setAddress(person.getAddress());
        entity.get().setGender(person.getGender());

        return DozerMapper.parseObject(personRepository.save(entity.get()), PersonVo.class);
    }

    public void delete(Long id) {
        logger.info("Deleting person");
        Optional<Person> entity = personRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException("No records found for this ID!");
        }
        personRepository.delete(entity.get());
    }

    public PersonVoV2 createV2(PersonVoV2 person) {
        logger.info("Creating people");
        var entiny = personMapperService.convertVoToEntity(person);
        return personMapperService.convertEntityToVo(personRepository.save(entiny));
    }
}
