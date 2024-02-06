package com.example.bakalaurinis.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSubmitRequest {
    private Long questionId;
    private String userAnswer;
    private Long selectedOptionId;
}
