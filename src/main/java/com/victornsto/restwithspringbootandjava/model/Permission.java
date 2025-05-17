package com.victornsto.restwithspringbootandjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission implements Serializable, GrantedAuthority {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }

//    @ManyToMany
//    @JoinTable(name = "user_permission",
//            joinColumns = @JoinColumn(name = "id_permission"),
//            inverseJoinColumns = @JoinColumn(name = "id_user"))
//    private Set<User> users = new LinkedHashSet<>();

}