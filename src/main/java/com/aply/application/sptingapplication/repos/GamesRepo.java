package com.aply.application.sptingapplication.repos;

import com.aply.application.sptingapplication.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GamesRepo extends JpaRepository<Games, Integer> {
    Optional<Games> findAllByGameName(String gameName);

    Games findGamesById(Integer id);

    List<Games> findByGenreId(Integer id);
}
