package com.example.bakalaurinis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Column(length = 5000)
    private String description;

    private String questionLevel;
    private String questionTopic;

    private Integer experience;
    private Integer coins;
    private String hint;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options;

}
