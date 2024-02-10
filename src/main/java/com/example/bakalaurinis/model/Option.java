package com.example.bakalaurinis.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne
    private Question question;
    private String text;
    private Boolean isCorrect; //nes del saugumo nesiunciamas i frontenda

    @JsonIgnore
    public Boolean getCorrect() {
        return isCorrect;
    }

    @JsonProperty
    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
