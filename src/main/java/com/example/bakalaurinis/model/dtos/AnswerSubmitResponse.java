package com.example.bakalaurinis.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSubmitResponse {
    private Boolean answerCorrect;
    private Integer updatedExperience;
    private Integer updatedCoins;
}
