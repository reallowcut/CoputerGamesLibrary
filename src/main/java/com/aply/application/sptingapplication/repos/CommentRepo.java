package com.aply.application.sptingapplication.repos;

import com.aply.application.sptingapplication.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comments, Integer> {
    List<Comments> findByGamesId(Integer id);
    Optional<Comments> findById(Integer id);
}
