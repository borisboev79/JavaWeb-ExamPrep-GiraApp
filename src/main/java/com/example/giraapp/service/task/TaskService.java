package com.example.giraapp.service.task;

import com.example.giraapp.model.TaskAddModel;
import com.example.giraapp.model.entity.Classification;
import com.example.giraapp.model.entity.Task;
import com.example.giraapp.repository.ClassificationRepository;
import com.example.giraapp.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ClassificationRepository classificationRepository;
    private final ModelMapper mapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, ClassificationRepository classificationRepository, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.classificationRepository = classificationRepository;
        this.mapper = mapper;
    }


    public void addTask(TaskAddModel taskAddModel) {
        Task task = this.mapper.map(taskAddModel, Task.class);
        Classification classification = this.classificationRepository.findByClassificationName(taskAddModel.getClassificationName()).orElse(null);
        task.setClassification(classification);

        this.taskRepository.saveAndFlush(task);



    }

}
