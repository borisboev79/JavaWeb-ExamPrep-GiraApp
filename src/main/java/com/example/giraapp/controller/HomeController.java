package com.example.giraapp.controller;

import com.example.giraapp.helper.LoggedUser;
import com.example.giraapp.model.TaskViewModel;
import com.example.giraapp.service.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {

    private final LoggedUser loggedUser;
    private final TaskService taskService;

    public HomeController(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }

    @GetMapping
    String getIndex(){return "index";}

    @GetMapping("/home")
    String getHome(Model model){

        if(loggedUser.getId() == null){
            return "index";
        }

        Set<TaskViewModel> tasks = this.taskService.getAllTasks();

        model.addAttribute("tasks", tasks);


        return "home";
    }

}
