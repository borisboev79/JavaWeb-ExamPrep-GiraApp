package com.example.giraapp.model;

import com.example.giraapp.model.entity.Classification;
import com.example.giraapp.model.enums.ClassificationName;
import com.example.giraapp.model.enums.ProgressStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskViewModel {

    private Long id;

    private String name;

    private String user;

    private ClassificationName classification;

    private LocalDate dueDate;

    private String progress;
}
