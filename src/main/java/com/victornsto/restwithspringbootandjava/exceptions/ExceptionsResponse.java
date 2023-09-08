package com.victornsto.restwithspringbootandjava.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter

public class ExceptionsResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;

    private String datails;

    public ExceptionsResponse(Date timestamp, String message, String datails) {
        this.timestamp = timestamp;
        this.message = message;
        this.datails = datails;
    }
}
