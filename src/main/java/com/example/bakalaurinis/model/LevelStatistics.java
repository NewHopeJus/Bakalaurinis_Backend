package com.example.bakalaurinis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class LevelStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String levelName;

    private Integer totalAnswered;

    private Integer correctlyAnswered;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "levelStatistics", cascade = CascadeType.ALL)
    private List<TopicStatistics> topicStatistics;

    @ManyToOne
    @JsonBackReference
    private User user;




}
