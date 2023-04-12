package com.example.giraapp.model.entity;

import com.example.giraapp.model.enums.ClassificationName;
import com.example.giraapp.model.enums.ProgressStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "classifications")
public class Classification extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ClassificationName classificationName;

    @Column(columnDefinition = "TEXT")
    private String description;

}
