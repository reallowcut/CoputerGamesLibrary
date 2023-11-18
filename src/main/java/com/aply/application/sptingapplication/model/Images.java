package com.aply.application.sptingapplication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;



@Data
@Entity
@EqualsAndHashCode(exclude = "games")
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "games_id")
    private Games games;

}
