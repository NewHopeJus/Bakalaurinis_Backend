package com.example.bakalaurinis.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {
    private Integer totalCorrectlyAnswered;
    private Integer totalAnswered;
    private List<LevelStatisticsDto> levelsStatistics;
}
