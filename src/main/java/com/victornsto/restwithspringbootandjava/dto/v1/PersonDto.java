package com.victornsto.restwithspringbootandjava.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.victornsto.restwithspringbootandjava.serializer.GenderSerializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class PersonDto extends RepresentationModel<PersonDto> implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Date birthDay;
    private String gender;
    private String foneNumber;
}
