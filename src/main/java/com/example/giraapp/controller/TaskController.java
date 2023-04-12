package com.example.giraapp.controller;

import com.example.giraapp.model.TaskAddModel;
import com.example.giraapp.model.enums.ClassificationName;
import com.example.giraapp.service.task.TaskService;
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
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @ModelAttribute(name = "taskAddModel")
    private TaskAddModel taskAddModel(){
        return new TaskAddModel();
    }

    @GetMapping("/add")
    public String getAddTask(Model model){
        model.addAttribute("taskTypes", ClassificationName.values());
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@Valid @ModelAttribute(name = "taskAddModel") TaskAddModel taskAddModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("taskAddModel", taskAddModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.taskAddModel", bindingResult);

            return "redirect:/tasks/add";
        }

        this.taskService.addTask(taskAddModel);

        return "redirect:/home";
    }


}
