package com.aply.application.sptingapplication.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public Roles() {
    }

    public Roles(Integer id, Role role) {
        this.id = id;
        this.role = role;
    }
}

