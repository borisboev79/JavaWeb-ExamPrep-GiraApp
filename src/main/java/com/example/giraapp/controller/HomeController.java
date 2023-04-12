package com.example.giraapp.controller;


import com.example.giraapp.helper.LoggedUser;
import com.example.giraapp.model.SongViewModel;
import com.example.giraapp.model.entity.Song;
import com.example.giraapp.service.song.SongService;
import com.example.giraapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final SongService songService;
    private final UserService userService;


    @Autowired
    public HomeController(LoggedUser loggedUser,
                          SongService songService, UserService userService) {

        this.loggedUser = loggedUser;
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView model) {

        if(loggedUser.getId() == null) {
            model.setViewName("index");
            return model;
        }

        List<SongViewModel> songs = this.songService.getAllSongs();
        Set<Song> playlist = this.userService.getPlaylist(this.loggedUser.getId());

        model.addObject("songs", songs);
        model.addObject("playlist", playlist);
        model.addObject("totalMin", playlist.stream().mapToInt(Song::getDuration).sum());

        model.setViewName("home");

        return model;
    }

    @GetMapping("/user/add/{id}")
    public String buyOffer(@PathVariable Long id) {

        this.userService.addSongToPlaylist(id);

        return "redirect:/home";
    }

}
