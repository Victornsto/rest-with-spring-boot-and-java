package com.victornsto.restwithspringbootandjava.integrationtests.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@XmlRootElement
public class TokenDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String acessToken;
    private String refreshToken;

    public TokenDto(String username, Boolean authenticated, Date created, Date expiration, String acessToken, String refreshToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.acessToken = acessToken;
        this.refreshToken = refreshToken;
    }

    public TokenDto() {}



}
