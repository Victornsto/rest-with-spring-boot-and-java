package com.victornsto.restwithspringbootandjava.dto.v1.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class EmailRequestDto implements Serializable {
    private String to;
    private String subject;
    private String body;
}
