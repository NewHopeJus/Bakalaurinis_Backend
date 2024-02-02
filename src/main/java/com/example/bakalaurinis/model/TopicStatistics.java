package com.example.bakalaurinis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long correctlyAnswered;
    private Long incorrectlyAnswered;
    private Long totalAnswered;

    @ManyToOne
    @JsonBackReference
    LevelStatistics levelStatistics;


}
