package com.example.giraapp.util.validations.validateUniqueTaskName;

import com.example.giraapp.model.binding.TaskAddModel;
import com.example.giraapp.model.entity.Task;
import com.example.giraapp.repository.TaskRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniqueTaskValidator implements ConstraintValidator<ValidateUniqueTask, TaskAddModel> {

    private final TaskRepository taskRepository;

    @Autowired
    public UniqueTaskValidator(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void initialize(ValidateUniqueTask constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TaskAddModel taskAddModel, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Task> task = this.taskRepository.findByName(taskAddModel.getName());
        return task.isEmpty();
    }
}
