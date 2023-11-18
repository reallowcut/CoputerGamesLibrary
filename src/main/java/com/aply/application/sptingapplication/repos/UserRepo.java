package com.aply.application.sptingapplication.repos;


import com.aply.application.sptingapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
   Optional<User> findByLogin(String login);

}
