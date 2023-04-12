package com.example.giraapp.model.binding;

import com.example.giraapp.model.enums.ClassificationName;
import com.example.giraapp.util.validations.validateUniqueTaskName.ValidateUniqueTask;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TaskAddModel {

    private Long id;

    @NotEmpty(message = "Task name cannot be empty.")
    @ValidateUniqueTask(message = "This task name already exists.")
    @Size(min = 3, max = 20, message = "Task name should be between 3 and 20 characters long")
    private String name;

    @NotEmpty
    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @NotNull
    private LocalDate dueDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClassificationName classificationName;

}
