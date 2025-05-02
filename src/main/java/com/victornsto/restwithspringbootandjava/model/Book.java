package com.victornsto.restwithspringbootandjava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "books")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "author", nullable = false, length = 80)
    String author;
    @Column(name = "title", nullable = false, length = 80)
    String title;
    @Column(name = "launch_date", nullable = false)
    Date launchDate;
    @Column(name = "price", nullable = false)
    Double price;
}
