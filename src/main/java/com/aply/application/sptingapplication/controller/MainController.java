package com.aply.application.sptingapplication.controller;

import com.aply.application.sptingapplication.model.*;
import com.aply.application.sptingapplication.repos.GamesRepo;
import com.aply.application.sptingapplication.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

// в main контроллере делаем функции которые не зависят от твоей роли


@Controller
@RequestMapping("/auth")
public class MainController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    GamesRepo gamesRepo;

    @GetMapping("/login")
    public String hello() {
        return "login";
    }

    @GetMapping("/hello")
    public String getHelloPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Optional<User> user = userRepo.findByLogin(login);
        User userObject = userRepo.getById(user.get().getId());
        List<Games> gamesList = gamesRepo.findAll();

        model.addAttribute("userObject", userObject);

        model.addAttribute("gameList", gamesList);


        return "hello";
    }

    @GetMapping("hello/sortGames")
    public String sortGames(int id, Model model) {

        if (id == 0) {
            return "redirect:/auth/hello";
        }
        List<Games> games = gamesRepo.findByGenreId(id);
        model.addAttribute("gameList", games);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Optional<User> user = userRepo.findByLogin(login);
        User userObject = userRepo.getById(user.get().getId());

        model.addAttribute("userObject", userObject);

        return "hello";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user, Model model) {
        Optional<User> username = userRepo.findByLogin(user.getLogin());
        if (username != null) {
            model.addAttribute("message", "User already exists");
        }
        user.setStatus(Status.ACTIVE);
        user.setRole(new Roles(2, Role.USER));
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        userRepo.save(user);
        System.out.println(user.toString());


        return "redirect:/auth/login";
    }

    @GetMapping("/back")
    public String goBack(String url) {
        return "redirect:" + url;

    }


}
