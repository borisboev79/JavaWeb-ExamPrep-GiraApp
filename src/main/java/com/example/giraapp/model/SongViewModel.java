package com.example.giraapp.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongViewModel {

    private Long id;

    private String performer;

    private String title;

    private Integer duration;

    private String style;
}
