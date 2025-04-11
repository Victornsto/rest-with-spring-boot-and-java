package com.victornsto.restwithspringbootandjava.converter;

import com.victornsto.restwithspringbootandjava.dto.v1.PersonDto;
import com.victornsto.restwithspringbootandjava.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoToPersonConverter implements Converter<PersonDto, Person> {

    @Override
    public Person convert(PersonDto source) {
        Person person = new Person();
        person.setId(source.getId());
        person.setFirstName(source.getFirstName());
        person.setLastName(source.getLastName());
        person.setAddress(source.getAddress());
        person.setGender(source.getGender());
        return person;
    }
}
