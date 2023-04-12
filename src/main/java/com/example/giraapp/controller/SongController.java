package com.example.giraapp.controller;

import com.example.giraapp.model.SongAddModel;
import com.example.giraapp.model.enums.Style;
import com.example.giraapp.service.song.SongService;
import com.example.giraapp.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final UserService userService;

    @Autowired
    public SongController(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }


    @ModelAttribute(name = "songAddModel")
    private SongAddModel songAddModel(){
        return new SongAddModel();
    }

    @GetMapping("/add")
    public String getAddSong(Model model){
        model.addAttribute("styles", Style.values());
        return "song-add";
    }

    @PostMapping("/add")
    public String addSong(@Valid @ModelAttribute(name = "songAddModel") SongAddModel songAddModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("songAddModel", songAddModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.songAddModel", bindingResult);

            return "redirect:/songs/add";
        }

        this.songService.addSong(songAddModel);

        return "redirect:/home";
    }

    @GetMapping("/remove")
    public String buyAllProducts() {

        this.userService.removeAllSongs();
        return "redirect:/home";
    }

}
