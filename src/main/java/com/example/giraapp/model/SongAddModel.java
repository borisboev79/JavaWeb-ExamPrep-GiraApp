package com.example.giraapp.model;

import com.example.giraapp.model.enums.Style;
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
public class SongAddModel {

    private Long id;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String performer;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String title;

    @NotNull
    @Positive
    private Integer duration;

    @PastOrPresent
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Style style;

}
