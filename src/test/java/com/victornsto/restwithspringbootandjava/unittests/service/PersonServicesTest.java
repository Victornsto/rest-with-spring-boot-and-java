package com.victornsto.restwithspringbootandjava.unittests.service;

import com.victornsto.restwithspringbootandjava.dto.v1.PersonDto;
import com.victornsto.restwithspringbootandjava.dto.v2.v1.PersonDtoV2;
import com.victornsto.restwithspringbootandjava.model.Person;
import com.victornsto.restwithspringbootandjava.repository.PersonRepository;
import com.victornsto.restwithspringbootandjava.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {
    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final ConversionService conversionService = mock(ConversionService.class);
    private final Pageable pageable = mock(Pageable.class);

    @InjectMocks
    PersonServices personServices = new PersonServices(personRepository, conversionService);
    Person mockPerson = new Person();
    PersonDto mockPersonDto = new PersonDto();

    @BeforeEach
    void setUp() {
        mockPerson.setId(1L);
        mockPerson.setFirstName("Victor");
        mockPerson.setLastName("Neto");
        mockPerson.setAddress("Rua 1");
        mockPerson.setBirthDay(new java.util.Date());
        mockPerson.setGender("Male");
        mockPerson.setEnabled(true);
        mockPerson.setBirthDay(new Date());
        mockPersonDto.setId(1L);
        mockPersonDto.setFirstName("Victor");
        mockPersonDto.setLastName("Neto");
        mockPersonDto.setAddress("Rua 1");
        mockPersonDto.setGender("Male");
        mockPersonDto.setEnabled(true);
        mockPersonDto.setBirthDay(new Date());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Disabled("Reason: Not implemented yet")
    void findAll() {
        when(personRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(mockPerson)));
        when(conversionService.convert(any(Person.class), eq(PersonDto.class))).thenReturn(mockPersonDto);
        Page<PersonDto> result = personServices.findAll(pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        PersonDto dto = result.getContent().get(0);
        assertEquals("Victor", dto.getFirstName());
        assertEquals("Neto", dto.getLastName());
        assertEquals("Rua 1", dto.getAddress());
        assertEquals("Male", dto.getGender());
        assertTrue(dto.hasLink("self"));
    }

    @Test
    void findAllByName() {
        when(personRepository.findByfirstNameContainingIgnoreCase(eq("Victor"), eq(pageable)))
                .thenReturn(new PageImpl<>(List.of(mockPerson)));
        when(conversionService.convert(any(Person.class), eq(PersonDto.class))).thenReturn(mockPersonDto);

        Page<PersonDto> result = personServices.findAllByName("Victor", pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        PersonDto dto = result.getContent().get(0);
        assertEquals("Victor", dto.getFirstName());
        assertEquals("Neto", dto.getLastName());
        assertEquals("Rua 1", dto.getAddress());
        assertEquals("Male", dto.getGender());
        assertTrue(dto.hasLink("self"));
    }

    @Test
    void findById() {
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(mockPerson));
        when(conversionService.convert(any(Person.class), eq(PersonDto.class))).thenReturn(mockPersonDto);

        PersonDto dto = personServices.findById("1");

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Victor", dto.getFirstName());
        assertEquals("Neto", dto.getLastName());
        assertEquals("Rua 1", dto.getAddress());
        assertEquals("Male", dto.getGender());
        assertEquals(true, dto.getEnabled());
        assertTrue(dto.hasLink("self"));
    }

    @Test
    void create() {
        when(conversionService.convert(any(PersonDto.class), eq(Person.class))).thenReturn(mockPerson);
        when(personRepository.save(any(Person.class))).thenReturn(mockPerson);
        when(conversionService.convert(any(Person.class), eq(PersonDto.class))).thenReturn(mockPersonDto);

        PersonDto dto = personServices.create(mockPersonDto);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Victor", dto.getFirstName());
        assertEquals("Neto", dto.getLastName());
        assertEquals("Rua 1", dto.getAddress());
        assertEquals("Male", dto.getGender());
        assertEquals(true, dto.getEnabled());
        assertTrue(dto.hasLink("self"));
    }

    @Test
    void createV2() {
        PersonDtoV2 mockPersonDtoV2 = new PersonDtoV2();
        mockPersonDtoV2.setId(1L);
        mockPersonDtoV2.setFirstName("Victor");
        mockPersonDtoV2.setLastName("Neto");
        mockPersonDtoV2.setAddress("Rua 1");
        mockPersonDtoV2.setBirthDay(new java.util.Date());
        mockPersonDtoV2.setGender("Male");


        when(conversionService.convert(any(PersonDtoV2.class), eq(Person.class))).thenReturn(mockPerson);
        when(personRepository.save(any(Person.class))).thenReturn(mockPerson);
        when(conversionService.convert(any(Person.class), eq(PersonDtoV2.class))).thenReturn(mockPersonDtoV2);

        PersonDtoV2 dto = personServices.createV2(mockPersonDtoV2);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Victor", dto.getFirstName());
        assertEquals("Neto", dto.getLastName());
        assertEquals("Rua 1", dto.getAddress());
        assertEquals("Male", dto.getGender());
    }

    @Test
    void update() {
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(mockPerson));
        when(personRepository.save(any(Person.class))).thenReturn(mockPerson);
        when(conversionService.convert(any(Person.class), eq(PersonDto.class))).thenReturn(mockPersonDto);

        PersonDto dto = personServices.update(mockPersonDto);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Victor", dto.getFirstName());
        assertEquals("Neto", dto.getLastName());
        assertEquals("Rua 1", dto.getAddress());
        assertEquals("Male", dto.getGender());
        assertEquals(true, dto.getEnabled());
        assertTrue(dto.hasLink("self"));
    }

    @Test
    void delete() {
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(mockPerson));
        when(conversionService.convert(any(Person.class), eq(PersonDto.class))).thenReturn(mockPersonDto);

        assertDoesNotThrow(() -> personServices.delete("1"));
    }
}