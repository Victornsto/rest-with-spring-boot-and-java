package com.victornsto.restwithspringbootandjava.converter;

import com.victornsto.restwithspringbootandjava.dto.v2.v1.PersonDtoV2;
import com.victornsto.restwithspringbootandjava.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoV2toPersonConverter implements Converter<PersonDtoV2, Person> {
    @Override
    public Person convert(PersonDtoV2 personDtoV2) {
        Person person = new Person();
        person.setId(personDtoV2.getId());
        person.setFirstName(personDtoV2.getFirstName());
        person.setLastName(personDtoV2.getLastName());
        person.setBirthDay(personDtoV2.getBirthDay());
        person.setAddress(personDtoV2.getAddress());
        person.setGender(personDtoV2.getGender());
        return person;
    }
}
