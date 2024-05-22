package com.example.bakalaurinis.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingDto {
    private String username;
    private Integer correctlyAnsweredCount;
    public RankingDto(String username, Integer correctlyAnsweredCount) {
        this.username = username;
        this.correctlyAnsweredCount = correctlyAnsweredCount;
    }
}
