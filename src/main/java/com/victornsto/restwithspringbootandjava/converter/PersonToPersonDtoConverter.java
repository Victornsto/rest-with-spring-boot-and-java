package com.victornsto.restwithspringbootandjava.converter;

import com.victornsto.restwithspringbootandjava.dto.v1.PersonDto;
import com.victornsto.restwithspringbootandjava.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {
    @Override
    public PersonDto convert(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        personDto.setAddress(person.getAddress());
        personDto.setGender(person.getGender());
        return personDto;
    }
}
