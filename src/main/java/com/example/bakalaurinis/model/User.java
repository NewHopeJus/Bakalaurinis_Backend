package com.example.bakalaurinis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    //nes adminas turi tureti ir userio ir admino privilegijas
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    private Integer userExperience;
    private Integer userCoins;
    @ElementCollection
    private Set<Long> correctAnswers = new HashSet<>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<LevelStatistics> levelStatistics;

    public User( String username, String password) {
        this.username = username;
        this.password = password;
        userCoins = 0;
        userExperience =0;
    }
}
