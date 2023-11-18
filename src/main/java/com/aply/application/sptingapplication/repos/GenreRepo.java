package com.aply.application.sptingapplication.repos;

import com.aply.application.sptingapplication.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre,Integer> {

}
