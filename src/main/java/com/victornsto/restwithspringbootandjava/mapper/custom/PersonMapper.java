package com.victornsto.restwithspringbootandjava.mapper.custom;

import com.victornsto.restwithspringbootandjava.model.Person;
import com.victornsto.restwithspringbootandjava.v2.PersonVoV2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonVoV2 convertEntityToVo(Person person) {
        PersonVoV2 vo = new PersonVoV2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }
    public Person convertVoToEntity(PersonVoV2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
//        entity.setBirthDay(new Date());
        entity.setfirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;
    }

}
