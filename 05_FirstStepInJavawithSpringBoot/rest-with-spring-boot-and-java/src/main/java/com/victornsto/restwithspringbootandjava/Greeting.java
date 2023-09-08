package com.victornsto.restwithspringbootandjava;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Greeting {
    private Long id;
    private String content;

    public Greeting() {
    }

    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }

}

