package com.victornsto.restwithspringbootandjava.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
public class BookDto extends RepresentationModel<BookDto> implements Serializable {
    private Long id;
    private String author;
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date launchDate;
    private Double price;
}
