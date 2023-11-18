package com.aply.application.sptingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "nickname")
    private String nickname;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "roles_id")
    private Roles role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}

