package com.victornsto.restwithspringbootandjava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
//@Table(name = "user_permission")
public class UserPermission {

    private User idUser;

//    @MapsId("idPermission")
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "id_permission", nullable = false)
    private Permission idPermission;

}