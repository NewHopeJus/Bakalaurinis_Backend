package com.example.bakalaurinis.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelStatisticsDto {
    private String levelName;
    private Integer levelCorrectAnswered;
    private Integer totalAnswered;
}
