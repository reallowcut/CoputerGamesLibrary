package com.aply.application.sptingapplication.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "genre_name")
    private String genreName;
}
