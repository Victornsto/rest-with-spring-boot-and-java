package com.victornsto.restwithspringbootandjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Person")
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;
    @Column(name = "birth_day", nullable = true)
    private Date birthDay;
    @Column(nullable = false, length = 200)
    private String address;
    @Column(nullable = false, length = 6)
    private String gender;
    @Column(nullable = true, length = 10)
    private Boolean enabled;

    @Size(max = 255)
    @ColumnDefault("'https://pub.erudio.com.br/meus-cursos'")
    @Column(name = "wikipedia_profile_url")
    private String wikipediaProfileUrl;

    @Size(max = 255)
    @ColumnDefault("'https://raw.githubusercontent.com/leandrocgsi/rest-with-spring-boot-and-java-erudio/refs/heads/main/photos/00_some_person.jpg'")
    @Column(name = "photo_url")
    private String photoUrl;

}
