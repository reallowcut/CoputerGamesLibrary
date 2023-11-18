package com.aply.application.sptingapplication.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_has_games")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "games_id")
    private Games games;
    @Column(name = "comment")
    private String comment;
    @Column(name="likes")
    private int likes;
    @Column(name = "dislikes")
    private int dislikes;
    @Column(name = "stars")
    private int stars;
    @Column(name = "reports")
    private int reports;

}
