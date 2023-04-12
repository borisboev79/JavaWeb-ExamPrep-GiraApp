package com.example.giraapp.model;

import com.example.giraapp.model.enums.ClassificationName;
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

    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    @NotEmpty
    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @NotNull
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ClassificationName classificationName;

}
