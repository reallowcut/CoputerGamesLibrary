package com.aply.application.sptingapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "games")
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "game")
    private String gameName;
    @Column(name = "midl_game_score")
    private double midlScore;
    @Column(name = "description")
    private String description;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    private String fileName;




}
