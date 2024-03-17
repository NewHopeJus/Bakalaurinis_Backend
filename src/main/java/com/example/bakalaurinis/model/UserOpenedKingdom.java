package com.example.bakalaurinis.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class UserOpenedKingdom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateTimeAnswered;
    @ManyToOne
    private User answeringUser;
    @ManyToOne
    private Question answeredQuestion;
    private boolean correctlyAnswered;
}
