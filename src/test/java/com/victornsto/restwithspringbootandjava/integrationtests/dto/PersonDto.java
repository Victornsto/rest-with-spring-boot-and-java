package com.victornsto.restwithspringbootandjava.integrationtests.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class PersonDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
}
