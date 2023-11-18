package com.aply.application.sptingapplication.repos;

import com.aply.application.sptingapplication.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesRepo extends JpaRepository<Images, Integer> {
    List<Images> findByGamesId(Integer id);
}
