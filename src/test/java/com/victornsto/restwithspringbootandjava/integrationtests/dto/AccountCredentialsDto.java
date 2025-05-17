package com.victornsto.restwithspringbootandjava.integrationtests.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountCredentialsDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String fullname;
}
