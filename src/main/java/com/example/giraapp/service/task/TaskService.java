package com.example.giraapp.service.task;

import com.example.giraapp.helper.LoggedUser;
import com.example.giraapp.model.TaskViewModel;
import com.example.giraapp.model.binding.TaskAddModel;
import com.example.giraapp.model.entity.Classification;
import com.example.giraapp.model.entity.Task;
import com.example.giraapp.model.entity.User;
import com.example.giraapp.model.enums.ProgressStatus;
import com.example.giraapp.repository.ClassificationRepository;
import com.example.giraapp.repository.TaskRepository;
import com.example.giraapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final LoggedUser loggedUser;

    private final ClassificationRepository classificationRepository;
    private final ModelMapper mapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, LoggedUser loggedUser, ClassificationRepository classificationRepository, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.classificationRepository = classificationRepository;
        this.mapper = mapper;
    }


    public void addTask(TaskAddModel taskAddModel) {
        Task task = this.mapper.map(taskAddModel, Task.class);
        User user = this.userRepository.findById(this.loggedUser.getId()).orElse(new User());
        Classification classification = this.classificationRepository.findByClassificationName(taskAddModel.getClassificationName()).orElse(null);
        task.setClassification(classification);
        task.setProgress(ProgressStatus.OPEN);
        task.setUser(user);

        this.taskRepository.saveAndFlush(task);



    }

    public Set<TaskViewModel> getAllTasks() {
        Set<TaskViewModel> tasks = new LinkedHashSet<>();
        List<Task> taskList = this.taskRepository.findAll();

        for (Task task : taskList) {
            TaskViewModel taskModel = new TaskViewModel();
            this.mapper.map(task, taskModel);
            taskModel.setUser(task.getUser().getUsername());

            tasks.add(taskModel);
        }

         return tasks;
    }

    @Transactional
    public void advanceProgress(Long id) {
        Task task = this.taskRepository.findById(id).orElseThrow();

        switch (task.getProgress()) {
            case OPEN -> {
                task.setProgress(ProgressStatus.IN_PROGRESS);
                this.taskRepository.saveAndFlush(task);
            }
            case IN_PROGRESS -> {
                task.setProgress(ProgressStatus.COMPLETED);
                this.taskRepository.saveAndFlush(task);
            }
            case COMPLETED -> this.taskRepository.delete(task);
        }


    }
}
