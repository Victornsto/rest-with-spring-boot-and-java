package com.victornsto.restwithspringbootandjava.converter;

import com.victornsto.restwithspringbootandjava.dto.v2.v1.PersonDtoV2;
import com.victornsto.restwithspringbootandjava.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDtoV2Converter implements Converter<Person, PersonDtoV2> {
    @Override
    public PersonDtoV2 convert(Person person) {
        PersonDtoV2 personDtoV2 = new PersonDtoV2();
        personDtoV2.setId(person.getId());
        personDtoV2.setFirstName(person.getFirstName());
        personDtoV2.setLastName(person.getLastName());
        personDtoV2.setBirthDay(person.getBirthDay());
        personDtoV2.setAddress(person.getAddress());
        personDtoV2.setGender(person.getGender());
        return personDtoV2;
    }
}
