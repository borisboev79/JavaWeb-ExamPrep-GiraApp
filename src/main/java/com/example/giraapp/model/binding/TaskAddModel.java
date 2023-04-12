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
@ValidateUniqueTask
public class TaskAddModel {

    private Long id;

    @NotEmpty

    @Size(min = 3, max = 20)
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
