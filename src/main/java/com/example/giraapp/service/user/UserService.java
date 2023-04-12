package com.example.giraapp.service.user;


import com.example.giraapp.helper.LoggedUser;
import com.example.giraapp.model.entity.User;
import com.example.giraapp.repository.TaskRepository;
import com.example.giraapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, TaskRepository taskRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.loggedUser = loggedUser;
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }


}
