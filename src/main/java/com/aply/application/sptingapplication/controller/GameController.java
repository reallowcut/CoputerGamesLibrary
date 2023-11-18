package com.aply.application.sptingapplication.controller;

import com.aply.application.sptingapplication.model.*;
import com.aply.application.sptingapplication.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/api")
public class GameController {
    @Autowired
    GamesRepo gamesRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    GenreRepo genreRepo;
    @Autowired
    ImagesRepo imagesRepo;


    @GetMapping("/games/{id}")
    public String gameComplements(Model model, @PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Optional<User> user = userRepo.findByLogin(login);
        User userObject = userRepo.getById(user.get().getId());
        Games games = gamesRepo.findGamesById(id);
        model.addAttribute("games", games);
        List<Comments> commentsList = commentRepo.findByGamesId(id);
        List<Images> img = imagesRepo.findByGamesId(id);
        model.addAttribute("images", img);
        model.addAttribute("comments", commentsList);

        List<User> users = userRepo.findAll();
        model.addAttribute("user", users);
        model.addAttribute("gameID", id);
        model.addAttribute("userObject", userObject);


        return "game";
    }

    @PostMapping("/games/sendcomment")
    public String sendComment(Model model, Comments comment, int gamesId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        Optional<User> userOptional = userRepo.findByLogin(login);
        User user = userRepo.getById(userOptional.get().getId());
        Games games = gamesRepo.findGamesById(gamesId);
        if (user == null) {

        } else {
            comment.setGames(games);
            comment.setUser(user);
        }
        System.out.println(user.getNickname());
        System.out.println(games.getGameName());
        commentRepo.save(comment);
        List<Comments> commentsList = commentRepo.findByGamesId(gamesId);
        double allStars = 0;
        for (Comments comments : commentsList) {
            allStars += comments.getStars();
        }
        allStars = allStars / commentsList.size();
        games.setMidlScore(allStars);
        gamesRepo.save(games);
        return "redirect:/api/games/" + gamesId;
    }

    @PostMapping("games/comment/{id}")
    public String deleteComment(@PathVariable int id, int gamesId) {
        commentRepo.deleteById(id);

        return "redirect:/api/games/" + gamesId;

    }

    @PostMapping("games/delete/{id}")
    public String deleteGame(@PathVariable int id) {
        imagesRepo.deleteAll();
        gamesRepo.deleteById(id);

        return "redirect:/auth/hello";
    }

    @GetMapping("games/create")
    public String goToCreateGamePage() {
        return "addGame";
    }

    @PostMapping("games/create")
    public String createGame(Games games, int genreId, @RequestParam("file1") MultipartFile file1) {

        System.out.println(file1.getSize());
        Genre genre = genreRepo.getById(genreId);
        games.setGenre(genre);
        gamesRepo.save(games);

        if (file1 != null && file1.getSize() != 0) {
            games.setFileName(file1.getOriginalFilename());
            System.out.println(games.getFileName());

        }
        gamesRepo.save(games);
        System.out.println(games.toString());
        return "redirect:/auth/hello";
    }

    @PostMapping("/games/addimg")
    public String addGameImg(@RequestParam("file") MultipartFile file, @RequestParam("gameId") int id, Images images) {

        System.out.println(images.toString());
        System.out.println(id);

        Games game = gamesRepo.findGamesById(id);

        System.out.println(game.toString());
        if (file != null && file.getSize() != 0) {

            images.setName(file.getOriginalFilename());
            images.setGames(game);
            imagesRepo.save(images);
        }
        System.out.println(images.toString());

        return "redirect:/api/games/" + game.getId();
    }
}
